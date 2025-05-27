import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.toiukha.groupactivity.dao.*;
import com.toiukha.groupactivity.entity.GroupActivityVO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class TestJDBCDAO extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		GroupActivityDAO actdao = new GroupActivityDAOJDBCimpl();
		
		// 新增活動
//		GroupActivityVO act1 = new GroupActivityVO();
//		act1.setActId(11);
//		act1.setActName("台北七星山健走團");
//		act1.setActDesc("適合闔家大小一起來賞芒草");
//		act1.setImgPath(null);
//		act1.setItnId(19);
//		act1.setHostId(29);
//		act1.setSignupStart(Timestamp.valueOf("2025-09-09 00:00:00"));
//		act1.setSignupEnd(Timestamp.valueOf("2025-09-19 00:00:00"));
//		act1.setMaxCap(6);
//		act1.setSignupCnt(0);
//		act1.setActStart(Timestamp.valueOf("2025-10-30 00:00:00"));
//		act1.setActEnd(Timestamp.valueOf("2025-10-30 00:00:00"));
//		act1.setIsPublic((byte) 0);
//		act1.setAllowCancel((byte)0);
//		act1.setRecruitStatus((byte)0);
//		actdao.add(act1);
		
		

		// 修改活動
//		GroupActivityVO act2 = new GroupActivityVO();
//		act2.setActId(11);
//		act2.setActName("台北七星山健走團");
//		act2.setActDesc("三人即成團");
//		act2.setImgPath(null);
//		act2.setItnId(19);
//		act2.setHostId(29);
//		act2.setSignupStart(Timestamp.valueOf("2025-09-09 00:00:00"));
//		act2.setSignupEnd(Timestamp.valueOf("2025-09-19 00:00:00"));
//		act2.setMaxCap(3);
//		act2.setSignupCnt(0);
//		act2.setActStart(Timestamp.valueOf("2025-10-30 00:00:00"));
//		act2.setActEnd(Timestamp.valueOf("2025-10-30 00:00:00"));
//		act2.setIsPublic((byte) 0);
//		act2.setAllowCancel((byte)0);
//		act2.setRecruitStatus((byte)0);	
//		actdao.update(act2);
		
		

		// 刪除
		actdao.delete(11);

		
		
		// 查詢單筆
		GroupActivityVO act4 = actdao.getByPK(10);
		System.out.print(act4.getActId() + ",");
		System.out.print(act4.getActName() + ",");
		System.out.print(act4.getActDesc() + ",");
		System.out.print(act4.getImgPath() + ",");
		System.out.print(act4.getItnId() + ",");
		System.out.print(act4.getHostId() + ",");
		System.out.print(act4.getSignupStart() + ",");
		System.out.print(act4.getSignupEnd() + ",");
		System.out.print(act4.getMaxCap() + ",");
		System.out.print(act4.getSignupCnt() + ",");
		System.out.print(act4.getActStart() + ",");
		System.out.print(act4.getActEnd() + ",");
		System.out.print(act4.getIsPublic() + ",");
		System.out.print(act4.getAllowCancel() + ",");
		System.out.print(act4.getRecruitStatus());
		System.out.println();
		
		
		System.out.println("---------------------");

		// 查詢多筆
		List<GroupActivityVO> actList1 = actdao.getAll();
		for (GroupActivityVO act : actList1) {
			System.out.print(act.getActId() + ",");
			System.out.print(act.getActName() + ",");
			System.out.print(act.getActDesc() + ",");
			System.out.print(act.getImgPath() + ",");
			System.out.print(act.getItnId() + ",");
			System.out.print(act.getHostId() + ",");
			System.out.print(act.getSignupStart() + ",");
			System.out.print(act.getSignupEnd() + ",");
			System.out.print(act.getMaxCap() + ",");
			System.out.print(act.getSignupCnt() + ",");
			System.out.print(act.getActStart() + ",");
			System.out.print(act.getActEnd() + ",");
			System.out.print(act.getIsPublic() + ",");
			System.out.print(act.getAllowCancel() + ",");
			System.out.print(act.getRecruitStatus());
			System.out.println();
		}
		
		
	}

}
