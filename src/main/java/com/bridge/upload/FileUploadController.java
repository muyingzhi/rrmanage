package com.bridge.upload;

import com.bridge.common.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 附件上传
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileUploadController {

	@Value("${file.filePath}")
	private String filePath;// 文件上传位置

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public AjaxResult<FileObj> upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request,
			String pid) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String nowYear = dateFormat.format(date).substring(0, 4);
				String nowMonth = dateFormat.format(date).substring(5, 7);
				String nowDay = dateFormat.format(date).substring(8, 10);
		try {
			// 获得原始文件名;
			String fileRealName = file.getOriginalFilename();
			// 点号的位置
			int pointIndex = fileRealName.lastIndexOf(".");
			// 截取文件后缀
			String fileSuffix = fileRealName.substring(pointIndex);
			// 生成上传文件名
			String fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileSuffix;
			// 以年月日分类上传文件夹
			String path = filePath + nowYear + File.separator + nowMonth + File.separator + nowDay + File.separator
					+ fileName;
			log.info("-------------------开始上传-------------------");
			File dest = new File(path);
			File dir = dest.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// 转存文件
			file.transferTo(new File(path));
			// 插入FJ表
			FileObj fj = new FileObj();
			String uid = UUID.randomUUID().toString().replaceAll("-", "");
			fj.setId(uid);
			fj.setPid(pid);
			fj.setPath(path.replace(filePath, ""));
			fj.setType(fileSuffix);
			fj.setYsmc(fileRealName);
			fj.setCreateTime(new Date());
			// bizFjDao.insertSelective(fj);
			log.info("-------------------上传成功-------------------");

			return AjaxResult.success(fj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("上传失败:" + e.getMessage());
		}
	}

	/**
	 * 删除上传附件
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/delete")
	public AjaxResult<String> deleteFj(String path) {
		try {

			File file = new File(path);
			file.delete();
			return AjaxResult.success("删除附件成功");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("删除附件失败" + e.getMessage());
		}
	}

	/**
	 * 查看图片信息
	 * 
	 * @param path
	 * @param response
	 */
	@RequestMapping("/image")
	public void image(String path, HttpServletResponse response) {
		InputStream in = null;
		try {
			String p = path.substring(path.indexOf(".")+1);
			response.setHeader("content-type", "image/"+ p);
			in = new FileInputStream(filePath + path);
			ServletOutputStream out = response.getOutputStream();
			int count = 0;
			byte[] data = new byte[1024];
			while ((count = in.read(data)) != -1) {
				out.write(data, 0, count);// 将缓冲区的数据输出到浏览器
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能描述：下载附件
	 * 
	 * FileObje 关键的属性要赋值：
	 *        path：文件路径
	 *        ysmc：文件原始名称，作为下载保存的默认名称；
	 * @param response
	 */
	@RequestMapping("/download")
	public String download(HttpServletResponse response, FileObj obj) {
		try {
			OutputStream outputStream = response.getOutputStream();
			File file = new File(filePath + obj.getPath());
			if (!file.isFile()) {
				return "文件不存在！";
			}
			InputStream in = new FileInputStream(filePath + obj.getPath());
			// 原始文件名
			String filename = obj.getYsmc();
			// 根据文件类型，定义contentType
			String filetype = obj.getType();
			if(!filetype.startsWith(".")){
				filetype= "."+filetype;
			}
			String contentType = "application/octet-stream";
			switch (filetype) {
				case ".xls":
					contentType = "application/vnd.ms-excel";
					break;
				case ".xlsx":
					contentType = "application/vnd.ms-excel";
					break;
				case ".doc":
					contentType = "application/msword";
					break;
				case ".docx":
					contentType = "application/msword";
					break;
			}
			response.setHeader("content-type", contentType);
			// 下载文件能正常显示中文
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
			// 实现文件下载
			int count = 0;
			byte[] data = new byte[1024];
			while ((count = in.read(data)) != -1) {
				outputStream.write(data, 0, count);// 将缓冲区的数据输出到浏览器
			}
			in.close();
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
