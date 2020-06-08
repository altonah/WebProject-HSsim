package com.HSsim.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author Altonah
 * 통합 DAO 작성중...(인서트, 딜리트, 업데이트, 셀렉트 ...)
 * 제약조건 	-	각 DB 테이블의 DTO/DAO클래스는 DTO/DAO를 상속받아 사용
 * 			-   DB의 테이블 명과 DTO/DAO 클래스명이 일치할 것. EX) 테이블명 : tn -> 클래스명 : TnDTO/TnDAO
 * 동작		-	DB의 테이블 명 -> 클래스 이름에서 DTO/DAO 삭제
 * 			-	클래스에 존재하는 GET/SET 메소드 검색(메소드명이 get/set으로 시작)하여 호출
 * 			-   ...
 */
public class DAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql="";
	
	// 디비 연결(커넥션 풀 사용)
	private Connection getConnection() throws Exception{
		// Context 객체를 생성
		Context init = new InitialContext();
		
		DataSource ds 
		 = (DataSource) init.lookup("java:comp/env/jdbc/BDGGDB");
		
		con = ds.getConnection();
		if(con!=null)
			System.out.println("DB MSG - DB연결됨...");
		return con;
	}
	
	// 자원 해제 	
	public void closeDB(){
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/////////////////제너릭 테에에에에에스스트트트///////////
	public <P> void printClassName(P p){
		System.out.println(p.getClass().getName());
	}
	//////////////////////////////////////////////
	
	public <P extends DTO> void insert(P dto){
		try {
			//1. DTO에 필드나 메소드가 없으면 리턴 ( DTO가 제대로 작성 됐으면 필요X )
			Method[] methodArr = dto.getMethodArr();
			if(dto.getFieldArr().length==0 || methodArr.length==0)
				return;
			
			//2. DB연결 및 SQL 작성 준비
			con = getConnection();
			sql = "insert into " + dto.getTableName();
			String column = "(";
			String values = " values(";
			
			//3. 메소드중 겟 메소드 호출 -> SQL에 추가
			int count=0;
			for(int i=0; i<methodArr.length; i++){
				if(methodArr[i].getName().startsWith("get")){
					column += methodArr[i].getName().substring(3) + ",";
					values += capQuotes(methodArr[i].invoke(dto)) + ",";
					count++;
				}
			}
			//4. 겟 메소드가 없으면 리턴 ( DTO가 제대로 작성 됐으면 필요 X )
			if(count==0){
				System.out.println("DB MSG - @@DTO에 게터 메서드 미존재@@");
				return;
			}
			//5. SQL에 불필요한 마지막 콤마 삭제 및 구문 마무리
			column = column.substring(0, column.length()-1);
			values = values.substring(0, values.length()-1);
			
			column += ")";
			values += ");";
			sql += column + values;
			
			//*. SQL 구문 확인
			System.out.println("DB MSG - " + sql);
			
			//6. 실행
			int check = con.prepareStatement(sql).executeUpdate();
			if(check == 1){
				System.out.println("DB MSG - insert 완료");
			}else{
				System.out.println("DB MSG - insert 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	//queryForWhere 파라미터 값 검사 필요 -> 전체 행 삭제 위험
	public void delete(String queryForWhere){
		try {
			//1. DB 연결 및 SQL 작성
			con = getConnection();
			
			String tableName = getClass().getSimpleName().replace("DAO", "");
			sql = "delete from " + tableName + " " + queryForWhere;
			
			//*. SQL 구문 확인
			System.out.println("DB MSG - " + sql);
			
			//2. 실행
			int check = con.prepareStatement(sql).executeUpdate();
			if(check == 1){
				System.out.println("DB MSG - delete 완료");
			}else{
				System.out.println("DB MSG - delete 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	public void update(String queryForSetAndWhere){
		try {
			//delete()와 동작 같음... 위 주석 참조
			con = getConnection();
			
			String tableName = getClass().getSimpleName().replace("DAO", "");
			sql = "update " + tableName + " " + queryForSetAndWhere;
			
			System.out.println("DB MSG - " + sql);
			
			int check = con.prepareStatement(sql).executeUpdate();
			if(check == 1){
				System.out.println("DB MSG - update 완료");
			}else{
				System.out.println("DB MSG - update 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	
	/**
	 * @param X -> select * from daoTable;
	 * @param String queryForWhere -> select * from daoTable 'queryForWhere';
	 * @return List<P> -> P extends DTO
	 * 제약 조건 : 쿼리 where에 JOIN 사용 불가
	 */
	public List<DTO> getListAll(){
		return getListAll("");
	}
	public <P extends DTO> List<P> getListAll(String queryForWhere){
		
		List<P> list = new ArrayList<P>();
		
		try {
			con = getConnection();
			
			String tableName = getClass().getSimpleName().replace("DAO", "");
			sql = "select * from " + tableName + " " + queryForWhere + ";";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			System.out.println("DB MSG - " + sql);
			

		    while(rs.next()){
		    	String dtoClassName = getClass().getName().replace("DAO", "DTO");
		    	
		    	//System.out.println("야호");
		    	
		    	Object obj = Class.forName(dtoClassName).newInstance();
		    	
		    	Method[] methodArr = obj.getClass().getMethods();
		    	
		    	for(int i=0; i<methodArr.length; i++){
		    		if(methodArr[i].getName().startsWith("set")){
		    			if(methodArr[i].getParameterTypes()[0] == int.class){
		    				methodArr[i].invoke(obj, rs.getInt(methodArr[i].getName().replace("set", "")));
		    			}else if(methodArr[i].getParameterTypes()[0] == String.class){
		    				methodArr[i].invoke(obj, rs.getString(methodArr[i].getName().replace("set", "")));
		    			}else if(methodArr[i].getParameterTypes()[0] == Date.class){
		    				methodArr[i].invoke(obj, rs.getDate(methodArr[i].getName().replace("set", "")));
		    			}
		    		}
		    	}

		    	//System.out.println("호잇");
		    	/*
		    	DTO dto = (DTO) dtoClass.newInstance();
		    	// DTO에 필드나 메소드가 없으면 리턴
				Method[] methodArr = dto.getMethodArr();
				if(dto.getFieldArr().length==0 || methodArr.length==0)
					return null;
		    	
		    	//셋 메소드일때 호출
				int count=0;
				for(int i=0; i<methodArr.length; i++){
					//System.out.println(methodArr[i]);
					if(methodArr[i].getName().startsWith("set")){
						Class<?> parameter = methodArr[i].getParameterTypes()[0];
						//파라미터가 String
						if(parameter == String.class){
							methodArr[i].invoke(dto, rs.getString(methodArr[i].getName().replace("set", "")));
						}
						//파라미터가 Integer
						else if(parameter == int.class){
							methodArr[i].invoke(dto, rs.getInt(methodArr[i].getName().replace("set", "")));
						}
						else if(parameter == Date.class){
							methodArr[i].invoke(dto, rs.getDate(methodArr[i].getName().replace("set", "")));
						}
						count++;
					}
				}
				if(count==0){
					System.out.println("@@셋터메소드 존재X@@");
					return null;
				}
		    	list.add(dto);
		    	*/
		    	list.add((P)obj);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	
	/**
	 * 파라메터(문자열)에 따옴표 씌움
	 * ex) text -> 'text'
	 * @param obj : 문자열
	 * @return obj
	 */
	Object capQuotes(Object obj){
		if(obj instanceof String){
			obj = "'" + obj + "'";
		}
		return obj;
	}
}
