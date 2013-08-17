package com.pc.pconsumption.resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.pc.pconsumption.framework.SystemGlobal;
import com.pc.pconsumption.po.ConsumptionPO;
import com.pc.pconsumption.service.ConsumptionService;
import com.pc.pconsumption.service.impl.ConsumptionServiceImpl;
import com.pc.pconsumption.util.ConfigKeys;
import com.pc.pconsumption.vo.ConsumptionVO;


@Path("/record")
public class ConsumptionResources {
	private transient static Logger log = Logger.getLogger(ConsumptionResources.class.getName());
	private ConsumptionService service = new ConsumptionServiceImpl();
	
	@POST
    @Path("/batchUpdateOrSave")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_HTML)
	public String save(Map<String,List<ConsumptionVO>> map)throws Exception{
		try {
			List<ConsumptionVO> as = map.get("add");
			List<ConsumptionVO> us = map.get("update");
			//批量添加
			service.saveBatch(as);
			//批量更新
			service.updateBatch(us);
		} catch (Exception e) {
			// TODO: 将异常抛到页面
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return "保存成功！";
	}
	/**
	 * @return
	 * @throws Excption
	 */
	@GET
	@Path("/queryAll")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject queryPOs()throws Exception{
		try {
			List<ConsumptionPO> ls = service.queryCons( SystemGlobal.getValue(ConfigKeys.JDBC_QUERY));
			return list2JsonObject(ls);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	@GET
	@Path("/query")
	public List<ConsumptionPO> get() throws Exception{
		List<ConsumptionPO> ls = service.queryCons( SystemGlobal.getValue(ConfigKeys.JDBC_QUERY));
		return ls;
	}
	private JSONObject list2JsonObject(List<ConsumptionPO> list) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			jo.put("count", list.size());
			JSONArray jsonArray = new JSONArray();
			for (ConsumptionPO po:list) {
				JSONObject object = new JSONObject();
				object.put("id", po.getId());
				object.put("everydayFee", po.getEverydayFee());
				object.put("groupByDate", po.getGroupByDate());
				object.put("lunchFee", po.getLunchFee());
				object.put("morningFee", po.getMorningFee());
				object.put("note", po.getNote());
				object.put("recordTime", po.getRecordTime());
				object.put("remark", po.getRemark());
				object.put("snackFee", po.getSnackFee());
				object.put("specialFee", po.getSpecialFee());
				object.put("supperFee", po.getSupperFee());
				object.put("totalFee", po.getTotalFee());
				object.put("trafficFee", po.getTrafficFee());
				jsonArray.put(object);
			}
			jo.put("rows", jsonArray);
			return jo; 
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	@Path("/donwloadExampleFile.txt")
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public StreamingOutput donwloadExampleFile(@Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		return new SubStreamingOutput(request,"/download/DataFormate.txt");
	}
	
	@SuppressWarnings("rawtypes")
	@Path("/batchImport")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	public String batchImport(@Context HttpServletRequest request,@Context HttpServletResponse response) throws Exception{
		  response.setContentType(MediaType.TEXT_HTML);
		  response.setCharacterEncoding("UTF-8");
		  try{  
		        final long MAX_SIZE = 1 * 1024 * 1024;// 设置上传文件最大为 1M  
		        // 允许上传的文件格式的列表  
		        response.setContentType("text/html");  
		        response.setCharacterEncoding("UTF-8"); // 设置字符编码为UTF-8, 这样支持汉字显示    
		        DiskFileItemFactory dfif = new DiskFileItemFactory();// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload    
		        dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘  
		        ServletFileUpload sfu = new ServletFileUpload(dfif); // 用以上工厂实例化上传组件    

		        sfu.setSizeMax(MAX_SIZE);// 设置最大上传尺寸    
		        List fileList = null; // 从request得到 所有 上传域的列表  
		        try {  
		            fileList = sfu.parseRequest(request);  
		        } catch (FileUploadException e) {// 处理文件尺寸过大异常  
		        	e.printStackTrace();  
		            if (e instanceof SizeLimitExceededException) {
		            	throw new Exception("文件大小不能超过1M");
		            }  
		        }  
		        // 得到所有上传的文件  
		        Iterator fileItr = fileList.iterator();  
		        // 循环处理所有文件  
		        while (fileItr.hasNext()) {
		            FileItem fileItem = null;  
		            String path = null;  
		            // 得到当前文件  
		            fileItem = (FileItem) fileItr.next();  
		            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)  
		            if (fileItem == null || fileItem.isFormField()) {  
		                continue;
		            }
		            // 得到文件的完整路径  
		            path = fileItem.getName();  
		            // 得到文件的大小  
		            if(!path.toLowerCase().endsWith(".txt") && !path.toLowerCase().endsWith(".xls")){
		            	throw new Exception("文件名后缀只能为txt 或者 xls ");
		            }
		            InputStream is =  fileItem.getInputStream();
		            if(path.toLowerCase().endsWith(".txt")){
		            	BufferedReader br = new BufferedReader(new InputStreamReader(is));
		            	//处理上传的 txt 文件
		            	dealFileTxt(br);
		            	if(br !=null){
		            		br.close();
		            	}
		            }
		            if(path.toLowerCase().endsWith(".xls")){
		            	HSSFWorkbook hssfWorkbook = new HSSFWorkbook( is);   
		            	// 循环 sheet 一般只有一个
		            	List<ConsumptionVO> vos = new ArrayList<ConsumptionVO>();
		            	for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){
		            		  // 当前 sheet 
		            	      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
		            	      
		            	      if(hssfSheet == null){  
		            	        continue;  
		            	      }  
		            	      // 循环行Row   
		            	      for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){ 
		            	    	  if(rowNum == 0){ //第一行文件头 不取
		            	    		  continue;
		            	    	  }
		            	    	  ConsumptionVO vo = new ConsumptionVO();
		            	    	  HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
		            	    	  if(hssfRow == null){
		            	    		  continue;  
		            	    	  }
		            	    	/** 0    1   2    3    4    5   6    7    8   9     10       11 
		            	    	 * 
	            	    		 * 早餐|中餐|晚餐|交通|日常|小吃|特殊|共计|评语|备注|记录时间|分组时间
	            	    		 * 4   |11.5|10 |3   |0   |0  |0   |28.5|A   |A   |2013-02-20|2013-02
	            	    		 * @param str 格式数据
	            	    		 */
		            	    	  String[] arr = new String[hssfRow.getLastCellNum()]; 
		            	    	  arr[0] = String.valueOf(hssfRow.getCell(0).getNumericCellValue() );
		            	    	  arr[1] = String.valueOf(hssfRow.getCell(1).getNumericCellValue() );
		            	    	  arr[2] = String.valueOf(hssfRow.getCell(2).getNumericCellValue() );
		            	    	  arr[3] = String.valueOf(hssfRow.getCell(3).getNumericCellValue() );
		            	    	  arr[4] = String.valueOf(hssfRow.getCell(4).getNumericCellValue() );
		            	    	  arr[5] = String.valueOf(hssfRow.getCell(5).getNumericCellValue() );
		            	    	  arr[6] = String.valueOf(hssfRow.getCell(6).getNumericCellValue() );
		            	    	  arr[7] = String.valueOf(hssfRow.getCell(7).getNumericCellValue() );
		            	    	  
		            	    	  arr[8] = String.valueOf(hssfRow.getCell(8).getStringCellValue() );
		            	    	  arr[9] = String.valueOf(hssfRow.getCell(9).getStringCellValue() );
		            	    	  arr[10] = String.valueOf(hssfRow.getCell(10).getStringCellValue() );
		            	    	  arr[11] = String.valueOf(hssfRow.getCell(11).getStringCellValue() );
		            	    	  
		            	    	  vo = getVO(arr);
		            	    	  vos.add(vo);
		            	      }
		            	    } 
		            	service.saveBatch(vos);
		            }
		            is.close();
		        }
		        
	        	return "{success:true,data:{clientName:'Fred. Olsen Lines'},portOfLoading:'FXT',portOfDischarge:'OSL111111'}";
	        	
		        }catch(Exception e){  
		            e.printStackTrace();
		            throw new Exception(e);
		        }
	}
	public void dealFileTxt(BufferedReader br) throws Exception{
		try {
			String line = "";
			List<ConsumptionVO> vos = new ArrayList<ConsumptionVO>();
			while((line = br.readLine())!=null){
				if(line.length() > 0){
					log.info("当前读取的数据为"+line);
					String[] datas = line.split("\\|");
					ConsumptionVO vo  = getVO(datas);
					vos.add(vo);
				}
			}
			br.close();
			service.saveBatch(vos);
		} catch (Exception e) {
			throw e;
		}
	}
	public static void main(String[] args) {
		System.out.println("当前读取的数据为");
	}
	public ConsumptionVO getVO(String[] datas){
		ConsumptionVO vo = new ConsumptionVO();
		vo.setMorningFee(Float.valueOf(datas[0].trim()));
		vo.setLunchFee(Float.valueOf(datas[1].trim()));
		vo.setSupperFee(Float.valueOf(datas[2].trim()));
		vo.setTrafficFee(Float.valueOf(datas[3].trim()));
		vo.setEverydayFee(Float.valueOf(datas[4].trim()));
		vo.setSnackFee(Float.valueOf(datas[5].trim()));
		vo.setSpecialFee(Float.valueOf(datas[6].trim()));
		float total = Float.valueOf(datas[7].trim());
		vo.setTotalFee( (total==0?vo.getTotalFee():total) );
		vo.setRemark(datas[8].trim());
		vo.setNote(datas[9].trim());
		vo.setRecordTime(datas[10].trim());
		vo.setGroupByDate(datas[11].trim());
		return vo;
	}
	
	/**用户导出Excel文件 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Path("/download/excel/exportExcelFile.xlsx")
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public StreamingOutput exportExcelFile(@Context HttpServletRequest request,
										   @Context HttpServletResponse response)throws Exception{
		
		return new SubStreamingOutput(request,"/download/DataFormate.txt");
	} 
	
	/**
	 * 用于导出PDF文件  文件的安全性 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Path("/download/pdf/exportPDFile.xlsx")
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public StreamingOutput exportPDFile(@Context HttpServletRequest request,@Context HttpServletResponse response)throws Exception{
		
		return new SubStreamingOutput(request,"/download/DataFormate.txt");
	} 
	/**
	 * 早餐|中餐|晚餐|交通|日常|小吃|特殊|共计|评语|备注|记录时间|分组时间
	 * 4   |11.5|10 |3   |0   |0  |0   |28.5|A   |A   |2013-02-20|2013-02
	 * @param str 格式数据
	 */
	private void parseStringPersisentToDB(String str) throws Exception{
		String[] datas = str.split("\\|");
		ConsumptionVO vo = new ConsumptionVO();
		vo.setMorningFee(Float.valueOf(datas[0]));
		vo.setLunchFee(Float.valueOf(datas[1]));
		vo.setSupperFee(Float.valueOf(datas[2]));
		vo.setTrafficFee(Float.valueOf(datas[3]));
		vo.setEverydayFee(Float.valueOf(datas[4]));
		vo.setSnackFee(Float.valueOf(datas[5]));
		vo.setSpecialFee(Float.valueOf(datas[6]));
		float total = Float.valueOf(datas[7]);
		vo.setTotalFee( (total==0?vo.getTotalFee():total) );
		vo.setRemark(datas[8]);
		vo.setNote(datas[9]);
		vo.setRecordTime(datas[10]);
		vo.setGroupByDate(datas[11]);
		service.save(vo);
	}
	
	class SubStreamingOutput implements StreamingOutput{
		private HttpServletRequest request;
		private String path;
		public SubStreamingOutput(HttpServletRequest request,String path) {
			this.request = request;
			this.path = path;
		}
		public void write(OutputStream outputStream) throws IOException,WebApplicationException {
			String file = request.getSession().getServletContext().getRealPath("/")+this.path;
		    FileInputStream inputStream = new FileInputStream(file);// 文件流
		    byte[] buffer = new byte[inputStream.available()];      //缓存大小
	        int i = -1;
	        while ((i = inputStream.read(buffer)) != -1) {
	        	outputStream.write(buffer, 0, i);
	        }
	        inputStream.close();
		}
	}

}
