<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/taglib.jsp" %>
<%@include file="/commons/public.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>SWFUpload Demos - Speed Demo</title>
	<link href="../css/default.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		table table td {
			width: 250px;
			white-space: nowrap;
			padding-right: 5px;
		}
		table table tr:nth-child(2n+1) {
			background-color: #EEEEEE;
		}
		table table td:first-child {
			font-weight: bold;
		}

		table table td:nth-child(2) {
			text-align: right;
			font-family: monospaced;
		}

	</style>
	<script type="text/javascript" src="${basePath}/js/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${basePath}/js/swfupload/swfupload.queue.js"></script>
	<script type="text/javascript" src="${basePath}/js/swfupload/swfupload.speed.js"></script>
	<script type="text/javascript" src="${basePath}/js/swfupload/handlers.js"></script>
	<script type="text/javascript">
		var swfu;
		window.onload = function() {
			var settings = {
				flash_url : basePath+"/js/swfupload/swfupload.swf",
				flash9_url : basePath+"/js/swfupload/swfupload_fp9.swf",
				upload_url: basePath+"/upload/upload",
				file_size_limit : "1 MB",
				file_types : "*.*",
				file_post_name : "file",
				file_types_description : "All Files",
				file_upload_limit : 100,
				file_queue_limit : 100,

				debug: false,

				// Button settings
				button_image_url: basePath+"/js/swfupload/imagebtn.png",
				button_width: "61",
				button_height: "22",
				button_placeholder_id: "spanButtonPlaceHolder",
				
				moving_average_history_size: 40,
				
				// The event handler functions are defined in handlers.js
				swfupload_preload_handler : preLoad,
				swfupload_load_failed_handler : loadFailed,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler: fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				upload_error_handler : uploadError,
				
				custom_settings : {
					tdFilesQueued : document.getElementById("tdFilesQueued"),
					tdFilesUploaded : document.getElementById("tdFilesUploaded"),
					tdErrors : document.getElementById("tdErrors"),
					tdCurrentSpeed : document.getElementById("tdCurrentSpeed"),
					tdAverageSpeed : document.getElementById("tdAverageSpeed"),
					tdMovingAverageSpeed : document.getElementById("tdMovingAverageSpeed"),
					tdTimeRemaining : document.getElementById("tdTimeRemaining"),
					tdTimeElapsed : document.getElementById("tdTimeElapsed"),
					tdPercentUploaded : document.getElementById("tdPercentUploaded"),
					tdSizeCount : document.getElementById("tdSizeCount"),
					tdSizeUploaded : document.getElementById("tdSizeUploaded"),
					tdSizeRemaining : document.getElementById("tdSizeRemaining"),
					tdProgressEventCount : document.getElementById("tdProgressEventCount")
				}
			};

			swfu = new SWFUpload(settings);
	     };
	</script>
</head>
<body>
	<span id="spanButtonPlaceHolder"></span>
	<div>
		<span>文件队列：</span>
		<span id="tdFilesQueued"></span>
	</div>			
	<div>
		<span>正在上传的文件：</span>
		<span id="tdFilesUploaded"></span>
	</div>			
	<div>
		<span>错误：</span>
		<span id="tdErrors"></span>
	</div>		
	<div>
		<span>当前上传的速度：</span>
		<span id="tdCurrentSpeed"></span>
	</div>			
	<div>
		<span>上传的平均速度：</span>
		<span id="tdAverageSpeed"></span>
	</div>			
	<div>
		<span>复制文件的速度：</span>
		<span id="tdMovingAverageSpeed"></span>
	</div>			
	<div>
		<span>剩余时间：</span>
		<span id="tdTimeRemaining"></span>
	</div>			
	<div>
		<span>消耗的时间：</span>
		<span id="tdTimeElapsed"></span>
	</div>			
	<div>
		<span>上传进度百分比：</span>
		<span id="tdPercentUploaded"></span>
	</div>			
	<div>
		<span>文件的总大小：</span>
		<span id="tdSizeCount"></span>
	</div>			
	<div>
		<span>上传的大小：</span>
		<span id="tdSizeUploaded"></span>
	</div>			
	<div>
		<span>剩余的大小：</span>
		<span id="tdSizeRemaining"></span>
	</div>			
	<div>
		<span>上传进度：</span>
		<span id="tdProgressEventCount"></span>
	</div>			
</body>
</html>
