<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="dh clearfix">
      <div class="dh_left fl"><img src="${basePath}/images/logo.png" alt=""/></div>
      <div id="t_p_1_1" class="dh_list fl">
           <ul>
           		<rz:if test="${rz:indexOf(pageContext.request.requestURI,'index')!=-1}">
           	   	 <li class="select"><a href="javascript:void(0);">首页</a></li>
           	   	 <rz:else/>
           	   	 <li><a href="${basePath}/index">首页</a></li>
           	    </rz:if>
           		<rz:if test="${rz:indexOf(pageContext.request.requestURI,'content/list')!=-1}">
		            <rz:permission method="list" model="content"><li class="select"><a href="javascript:void(0);">内容管理</a></li></rz:permission>
           		<rz:else/>
           			<rz:permission method="list" model="content"><li><a href="${basePath}/admin/content/list">内容管理</a></li></rz:permission>
           		</rz:if>
           		
           		<rz:if test="${rz:indexOf(pageContext.request.requestURI,'channel/list')!=-1}">
		            <rz:permission method="list" model="content"><li class="select"><a href="javascript:void(0);">栏目管理</a></li></rz:permission>
           		<rz:else/>
           			<rz:permission method="list" model="content"><li><a href="${basePath}/admin/channel/list">栏目管理</a></li></rz:permission>
           		</rz:if>
           		
           		<rz:if test="${rz:indexOf(pageContext.request.requestURI,'interaction/list')!=-1}">
		            <rz:permission method="list" model="content"><li class="select"><a href="javascript:void(0);">互动管理</a></li></rz:permission>
           		<rz:else/>
           			<rz:permission method="list" model="content"><li><a href="${basePath}/admin/interaction/list">互动管理</a></li></rz:permission>
           		</rz:if>
           		
           		
           		<rz:if test="${rz:indexOf(pageContext.request.requestURI,'stat/list')!=-1}">
		            <rz:permission method="list" model="content"><li class="select"><a href="javascript:void(0);">统计管理</a></li></rz:permission>
           		<rz:else/>
           			<rz:permission method="list" model="content"><li><a href="${basePath}/admin/stat/list">统计管理</a></li></rz:permission>
           		</rz:if>
           		
           		<rz:if test="${rz:indexOf(pageContext.request.requestURI,'params/list')!=-1}">
		            <rz:permission method="list" model="content"><li class="select"><a href="javascript:void(0);">参数管理</a></li></rz:permission>
           		<rz:else/>
           			<rz:permission method="list" model="content"><li><a href="${basePath}/admin/params/list">参数管理</a></li></rz:permission>
           		</rz:if>
           </ul>
      </div>
      <a href="${basePath}/logout">退出</a>
 </div>