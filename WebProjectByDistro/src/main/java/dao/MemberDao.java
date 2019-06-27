package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import exception.BoardIdNotMatchException;
import exception.ReplyIdNotMatchException;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//회원 가입
	public void join(RegisterCommand command) {
		jdbcTemplate.update("insert into MEMBER (ID, PASSWORD, EMAIL, NAME, REGDATE) values (?, ?, ?, ?, ?)",
			command.getId(),
			command.getPassword(),
			command.getEmail(),
			command.getName(),
			Timestamp.valueOf(LocalDateTime.now())
		);
	}
	
	//로그인 처리
	public Member login(LoginCommand command) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where ID = ? and PASSWORD = ?", new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(
					rs.getString("ID"),
					rs.getString("PASSWORD"),
					rs.getString("EMAIL"),
					rs.getString("NAME")
				);
					return member;
			}
		}, command.getId(), command.getPassword());
		return results.isEmpty() ? null : results.get(0);
	}
	
	//암호 변경
	public void update(ChangePasswordCommand command, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		jdbcTemplate.update("update MEMBER set PASSWORD = ? where ID = ?", command.getNewPassword(), member.getId());
	}
	
	//ID 정보로 데이터 얻기(중복 확인)
	public Member selectById(String id) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where ID = ?", new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(
					rs.getString("ID"),
					rs.getString("PASSWORD"),
					rs.getString("EMAIL"),
					rs.getString("NAME")
				);
				return member;
			}
		}, id);
		return results.isEmpty() ? null : results.get(0);
	}
	
	//게시판 리스트 출력
	public List<BoardList> selectBoardList() {
		List<BoardList> results = jdbcTemplate.query("select * from MEMBER_CONTENT order by POSTNUMBER desc", new RowMapper<BoardList>() {
			@Override
			public BoardList mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardList list = new BoardList(
					rs.getString("TITLE"),
					rs.getString("CONTENT"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime(),
					rs.getInt("HITS"),
					rs.getInt("POSTNUMBER")
				);
				return list;
			}
		});
		
		return results;
	}
	
	//게시글 등록
	public void write(WriteCommand writeCommand, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		jdbcTemplate.update("insert into MEMBER_CONTENT (TITLE, CONTENT, REGDATE, NAME, HITS) values (?, ?, ?, ?, ?)",
			writeCommand.getTitle(),
			writeCommand.getContent(),
			LocalDateTime.now(),
			member.getName(),
			0
		);
	}
	
	//게시글 읽기
	public ReadContentCommand readByPostNumber(int no) {
		List<ReadContentCommand> results = jdbcTemplate.query("select * from MEMBER_CONTENT where POSTNUMBER = ?", new RowMapper<ReadContentCommand>() {
			@Override
			public ReadContentCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReadContentCommand command = new ReadContentCommand(
					rs.getString("TITLE"),
					rs.getString("CONTENT"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime()
				);
				
				return command;
			}
		}, no);
		
		increaseHits(no);
		return results.isEmpty() ? null : results.get(0);
	}
	
	//게시물 삭제를 위한 게시물 읽기
	public ReadContentCommand readForDelete(int no) {
		List<ReadContentCommand> results = jdbcTemplate.query("select * from MEMBER_CONTENT where POSTNUMBER = ?", new RowMapper<ReadContentCommand>() {
			@Override
			public ReadContentCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReadContentCommand command = new ReadContentCommand(
					rs.getString("TITLE"),
					rs.getString("CONTENT"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime()
				);
				
				return command;
			}
		}, no);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	//조회수 증가
	public void increaseHits(int no) {
		jdbcTemplate.update("update MEMBER_CONTENT set HITS = HITS + 1 where POSTNUMBER = ?", no);
	}
	
	//게시글 수정
	public ReadContentCommand modifyBefore(int no) {
		List<ReadContentCommand> results = jdbcTemplate.query("select * from MEMBER_CONTENT where POSTNUMBER = ?", new RowMapper<ReadContentCommand>() {
			@Override
			public ReadContentCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReadContentCommand command = new ReadContentCommand(
					rs.getString("TITLE"),
					rs.getString("CONTENT"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime()
				);
				
				return command;
			}
		}, no);
		return results.isEmpty() ? null : results.get(0);
	}
	
	//게시글 수정
	public void modifyAfter(ReadContentCommand content, int no, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		int result = jdbcTemplate.update("update MEMBER_CONTENT set TITLE = ?, CONTENT = ? where POSTNUMBER = ? and NAME = ?",
			content.getTitle(),
			content.getContent(),
			no,
			member.getName()
		);
		if(result == 0) {
			throw new BoardIdNotMatchException();
		}
	}
	
	//게시물 삭제
	public void delete(int no, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		int result = jdbcTemplate.update("delete from MEMBER_CONTENT where POSTNUMBER = ? and NAME = ?", no, member.getName());
		if(result == 0) {
			throw new BoardIdNotMatchException();
		}
	}
	
	//답글 쓰기
	public void writeReply(ReplyWriteCommand command) {
		jdbcTemplate.update("insert into MEMBER_REPLY (POSTNUMBER, REPLY, NAME, REGDATE) values(?, ?, ?, ?)", 
			command.getPostNumber(), 
			command.getReply(),
			command.getName(),
			LocalDateTime.now()
		);
	}
	
	//게시글 번호에 따른 답글 리스트 읽어오기
	public List<ReplyReadCommand> readReply(int no) {
		List<ReplyReadCommand> results = jdbcTemplate.query("select * from MEMBER_REPLY where POSTNUMBER = ?", new RowMapper<ReplyReadCommand>() {

			@Override
			public ReplyReadCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReplyReadCommand command = new ReplyReadCommand(
					rs.getInt("NUMBER"),
					rs.getString("REPLY"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime()
				);
				
				return command;
			}
			
		}, no);
		
		return results;
	}
	
	//댓글 삭제를 위한 답글 읽어오기
	public ReplyReadCommand readReplyForDeleteReply(int no, int number) {
		List<ReplyReadCommand> results = jdbcTemplate.query("select * from MEMBER_REPLY where POSTNUMBER = ? and NUMBER = ?", new RowMapper<ReplyReadCommand>() {
			@Override
			public ReplyReadCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
				ReplyReadCommand command = new ReplyReadCommand(
					rs.getInt("NUMBER"),
					rs.getString("REPLY"),
					rs.getString("NAME"),
					rs.getTimestamp("REGDATE").toLocalDateTime()
				);
				
				return command;
			}
		}, no, number);
		
		return results.isEmpty() ? null : results.get(0);
	}
	
	//답글 수정
	public void modifyReply(int no, int number, HttpServletRequest request, ReplyModifyCommand command) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		int result = jdbcTemplate.update("update MEMBER_REPLY set REPLY = ? where POSTNUMBER = ? and NUMBER = ? and NAME = ?", 
			command.getReply(),
			no,
			number,
			member.getName()
		);
		if(result == 0) {
			throw new ReplyIdNotMatchException();
		}
	}
	
	//답글 삭제
	public void DeleteReply(int no, int number, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		int result = jdbcTemplate.update("delete from MEMBER_REPLY where POSTNUMBER = ? and NUMBER = ? and NAME = ?",
			no,
			number,
			member.getName()
		);
		if(result == 0) {
			throw new ReplyIdNotMatchException();
		}
	}
	
}