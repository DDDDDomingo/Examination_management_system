package studio.beita.hdxg.beitasystem.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.beita.hdxg.beitasystem.config.ServiceException;
import studio.beita.hdxg.beitasystem.model.domain.TaskInfo;
import studio.beita.hdxg.beitasystem.service.impl.TaskServiceImpl;
import studio.beita.hdxg.beitasystem.utils.ResultInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zr
 * 任务管理
 */
@Controller
public class TaskManageController {
	@Autowired
	private TaskServiceImpl taskServiceImpl;

	/**
	 * index.jsp
	 */
	@RequestMapping(value={"", "/", "index"})
	public String info(){
		return "index";
	}
	
	/**
	 * 任务列表
	 */
	@ResponseBody
	@RequestMapping(value="list", method= RequestMethod.POST)
	public String list(){
		Map<String, Object> map = new HashMap<>(16);
		List<TaskInfo> infos = taskServiceImpl.list();
		map.put("rows", infos);
		map.put("total", infos.size());
		return JSON.toJSONString(map);
	}
	
	/**
	 * 保存定时任务
	 * @param info
	 */
	@ResponseBody
	@RequestMapping(value="save", method= RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public String save(TaskInfo info){
		try {
			if(info.getId() == 0) {
				taskServiceImpl.addJob(info);
			}else{
				taskServiceImpl.edit(info);
			}
		} catch (ServiceException e) {
			return ResultInfo.error(-1, e.getMessage());
		}
		return ResultInfo.success();
	}
	
	/**
	 * 删除定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="delete/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public String delete(@PathVariable String jobName, @PathVariable String jobGroup){
		try {
			taskServiceImpl.delete(jobName, jobGroup);
		} catch (ServiceException e) {
			return ResultInfo.error(-1, e.getMessage());
		}
		return ResultInfo.success();
	}
	
	/**
	 * 暂停定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="pause/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public String pause(@PathVariable String jobName, @PathVariable String jobGroup){
		try {
			taskServiceImpl.pause(jobName, jobGroup);
		} catch (ServiceException e) {
			return ResultInfo.error(-1, e.getMessage());
		}
		return ResultInfo.success();
	}
	
	/**
	 * 重新开始定时任务
	 * @param jobName
	 * @param jobGroup
	 */
	@ResponseBody
	@RequestMapping(value="resume/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public String resume(@PathVariable String jobName, @PathVariable String jobGroup){
		try {
			taskServiceImpl.resume(jobName, jobGroup);
		} catch (ServiceException e) {
			return ResultInfo.error(-1, e.getMessage());
		}
		return ResultInfo.success();
	}
}
