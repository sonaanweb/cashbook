package cash.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;


@WebServlet("/calendar")
public class CalendarController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사
		String memberId = "user"; // session 안 로그인정보를 가져와야 함 
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance();
		// 출력하고자 하는 년도와 월의 기본값은 이번달 1일
		firstDay.set(Calendar.DATE, 1);
		
		if(request.getParameter("targetYear") != null
			&& request.getParameter("targetMonth") != null) {
			
		// 사용자가 보고자 하는(target) - 출력하려는 년도 / 월이 매개값으로 넘어왔다면
		// (year가 먼저 있어야 함)
		firstDay.set(Calendar.YEAR, Integer.parseInt(request.getParameter("targetYear")));
		// API에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월은 1, 년 +1
		// API에서 자동으로 Calendar.MONTH값으로 -1이 입력되면 월은 12, 년 -1
		firstDay.set(Calendar.MONTH, Integer.parseInt(request.getParameter("targetMonth")));
		}
		int targetYear = firstDay.get(Calendar.YEAR); // 오늘 날짜의 해당 년 / 월
		int targetMonth = firstDay.get(Calendar.MONTH);
		
		// 달력 출력시 시작 공백 개수 - > 1일 날짜의 요일값(일1, 월2, 토6..) - 1
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) -1;
		System.out.println(beginBlank+"<- beginBlank");
		
		// 마지막 공백 - 출력되는 달의 마지막 날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		System.out.println(lastDate+"<- lastDate");
		
		// 달력출력시 마지막 날짜 출력 후 공백 개수 -> 전체 출력 셀의 개수가 7로 나누어 떨어져야 한다
		int endBlank = 0;
		if((( beginBlank + lastDate)% 7) != 0){
			endBlank = 7 - ((beginBlank + lastDate)%7);
		}
		int totalCell = beginBlank + lastDate + endBlank;
		System.out.println(totalCell+"<- totalCell");
		System.out.println(endBlank+"<- endBlank");
		
		
		// 모델 호출(DAO 타켓 월의 수입/지출 데이터)
		List<Cashbook> list = new CashbookDao().selectCashbookListByMonth(memberId, targetYear, targetMonth+1);
		
		
		List<Map<String, Object>> htList
		= new HashtagDao().selectWordCountByMonth(memberId, targetYear, targetMonth+1);
		System.out.println(htList.size());
		
		// 뷰에 값 넘기기(requset 속성)
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		
		// list 뷰 (수입.지출...)
		request.setAttribute("list", list);
		request.setAttribute("htList", htList);
		
		// 달력을 출력하는 뷰 (데이터를 그대로 넘겨준다(forward))
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}

}
