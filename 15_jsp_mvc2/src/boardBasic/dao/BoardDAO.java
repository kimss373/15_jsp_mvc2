package boardBasic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import boardBasic.dto.BoardDTO;

public class BoardDAO {

	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}

	private Connection conn         = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs            = null;

	public void getConnection() {
		
		try {
			
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");       // lookup 메서드를 통해 context.xml 파일에 접근하여 자바환경 코드를 검색
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool"); 		  // <Context>태그안의 <Resource> 환경설정의 name이 jdbc/pool인 것을 검색
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void getClose() {
		
    	if (rs != null)    {try {rs.close();}   catch (SQLException e) {}}
    	if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
        if (conn != null)  {try {conn.close();}  catch (SQLException e) {}}
        
	}
	
	public ArrayList<BoardDTO> getBoardList() {
		
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setNum(rs.getInt("NUM"));
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setPassword(rs.getString("PASSWORD"));
				boardDTO.setEnrollDt(rs.getDate("ENROLL_DT"));
				boardDTO.setReadCnt(rs.getInt("READ_CNT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				
				boardList.add(boardDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return boardList;
		
	}
	
	public void insertBoard(BoardDTO boardDTO) {
		
		try {
			getConnection();
			String sql = "INSERT INTO BOARD(WRITER, EMAIL, SUBJECT, PASSWORD, ENROLL_DT, READ_CNT, CONTENT) VALUES(?, ?, ?, ?, NOW(), 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDTO.getWriter());
			pstmt.setString(2, boardDTO.getEmail());
			pstmt.setString(3, boardDTO.getSubject());
			pstmt.setString(4, boardDTO.getPassword());
			pstmt.setString(5, boardDTO.getContent());
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}
	
	
	public BoardDTO getBoardDetail(int num, boolean isIncreaseReadCnt) {
		
		BoardDTO boardDTO = new BoardDTO();
		
		try {
			getConnection();
			
			if(isIncreaseReadCnt) {
				
				pstmt = conn.prepareStatement("UPDATE BOARD SET READ_CNT = READ_CNT+1 WHERE NUM = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
			}
			
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				boardDTO.setNum(rs.getInt("NUM"));
				boardDTO.setWriter(rs.getString("WRITER"));
				boardDTO.setEmail(rs.getString("EMAIL"));
				boardDTO.setSubject(rs.getString("SUBJECT"));
				boardDTO.setPassword(rs.getString("PASSWORD"));
				boardDTO.setEnrollDt(rs.getDate("ENROLL_DT"));
				boardDTO.setReadCnt(rs.getInt("READ_CNT"));
				boardDTO.setContent(rs.getString("CONTENT"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return boardDTO;
		
	}
	
	public boolean checkValidateMember(BoardDTO boardDTO) {
		
		boolean isValidateMember = false;
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM BOARD WHERE NUM = ? AND PASSWORD = ?");
			pstmt.setInt(1, boardDTO.getNum());			
			pstmt.setString(2, boardDTO.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isValidateMember = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		
		return isValidateMember;
		
	}
	
	
	public boolean updateBoard(BoardDTO boardDTO) {
		
		boolean isUpdate = false;
		
		try {
			if (checkValidateMember(boardDTO)) {
				getConnection();
				pstmt = conn.prepareStatement("UPDATE BOARD SET SUBJECT = ?, CONTENT = ?, ENROLL_DT = NOW() WHERE NUM = ?");
				pstmt.setString(1, boardDTO.getSubject());
				pstmt.setString(2, boardDTO.getContent());
				pstmt.setInt(3, boardDTO.getNum());
				pstmt.executeUpdate();
				isUpdate = true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		
		return isUpdate;
	}
	
	public boolean deleteBoard(BoardDTO boardDTO) {
	
		boolean isDelete = false;
		
		try {
			if (checkValidateMember(boardDTO)) {
				getConnection();
				pstmt = conn.prepareStatement("DELETE FROM BOARD WHERE NUM = ?");
				pstmt.setInt(1, boardDTO.getNum());
				pstmt.executeUpdate();
				isDelete = true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return isDelete;
		
	}
	
	
}
