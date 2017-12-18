package finaltp.paging;

public class AjaxPageModule {

	// 프로그램이 시작하는 시점에서 메모리에 올라가기 때문에 객체를 만들지 않고도 사용이 가능! = static
		public static String makePage(String pagename,int totalCnt,int listSize,int pageSize,int cp) { 
		      
		      StringBuffer sb=new StringBuffer();

		      int totalPage=totalCnt/listSize+1;
		      if(totalCnt%listSize==0) {
		    	  if(totalCnt!=0) {
		    	  totalPage--;
		    	  }
		      }
		      int userGroup=cp/pageSize;
		      if(cp%pageSize==0)userGroup--;
		      
		      //왼쪽으로 가는 화살표
		      if(userGroup!=0){
		         sb.append("<a href='");
		         sb.append(pagename);
		         sb.append("?cp=");
		         int temp=(userGroup-1)*pageSize+pageSize;
		         sb.append(temp);
		         sb.append("'>&lt;&lt;</a>");
		      }
		      //숫자 출력
		      for(int i=(userGroup*pageSize+1); i<=(userGroup*pageSize+pageSize); i++){
		         sb.append("&nbsp;&nbsp;<a href='");
		         sb.append(pagename);
		         sb.append("&cp=");
		         sb.append(i);
		         sb.append("'>");
		         sb.append(i);
		         sb.append("</a>&nbsp;&nbsp;");
		         if(i==totalPage)break;
		      }
		      
		      //오른쪽으로 가는 화살표
		      
		      if(userGroup!=(totalPage/pageSize-(totalPage%pageSize==0?1:0))){
		         sb.append("<a href='");
		         sb.append(pagename);
		         sb.append("?cp=");
		         int temp=(userGroup+1)*pageSize+1;
		         sb.append(temp);
		         sb.append("'>&gt;&gt;</a>");
		      }
		      
		      return sb.toString();
		      
		   }
	
}
