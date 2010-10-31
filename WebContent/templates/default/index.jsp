<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${pageTitle}</title>

    <link rel="stylesheet" href="${templatePath}/css/main.css" type="text/css" />
    <link rel="stylesheet" href="${templatePath}/js/windowjs/themes/default.css" type="text/css" />
    <link rel="stylesheet" href="${templatePath}/js/windowjs/themes/alphacube.css" type="text/css" />

    <c:choose>
        <c:when test="${config.useCDN}">
            <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/prototype/1.6.1.0/prototype.js"></script>
        </c:when>
        <c:otherwise>
            <script type="text/javascript" src="${templatePath}/js/prototype.js"></script>
        </c:otherwise>
    </c:choose>
	
	<script type="text/javascript" src="${templatePath}/js/autocomplete.js"></script>
	<script type="text/javascript" src="${templatePath}/js/tiny_mce/tiny_mce.js"></script>
	<script type="text/javascript" src="${templatePath}/js/scriptaculous.js"></script>
	<script type="text/javascript" src="${templatePath}/js/ddmenu.js"></script>
	<script type="text/javascript" src="${templatePath}/js/windowjs/javascripts/window.js"></script>
	<script type="text/javascript" src="${templatePath}/js/tiny_mce/plugins/asciimath/js/ASCIIMathMLwFallback.js"></script>
	<script type="text/javascript">
	var AMTcgiloc = '${config.texCgi}';
	</script>
	<script type="text/javascript">
	   Element.observe(window, 'load', function() {
		tinyMCE.init({
		    mode : "textareas",
		    //skin : "o2k7",
		    theme : "advanced",
		    theme_advanced_buttons1 : 'bold,italic,underline,strikethrough,link,unlink,separator,image,asciimath,asciimathcharmap,separator,numlist,bullist,separator,emotions,separator,cleanup,code',
		    theme_advanced_buttons2 : "tablecontrols",
		    theme_advanced_buttons3 : "",
		    theme_advanced_resizing : true,
		    plugins : "inlinepopups,asciimath,emotions,table",
		    table_styles : "Header 1=header1;Header 2=header2;Header 3=header3",
		    table_cell_styles : "Header 1=header1;Header 2=header2;Header 3=header3;Table Cell=tableCel1",
		    table_row_styles : "Header 1=header1;Header 2=header2;Header 3=header3;Table Row=tableRow1",
		    //TODO: change!		    	   
	        content_css : "${templatePath}/css/editor-content.css"
		});
	   });
	</script>
	
	<script type="text/javascript" src="${homeDir}/includes/common.js"></script>
	<script type="text/javascript" src="${templatePath}/js/main.js"></script>
	<script type="text/javascript">
	<!-- // initialize global variables
	   articlePath = '${config.articlePath}';
	   actionPath = '${config.actionPath}';
	   apiPath = '${config.apiPath}';
	   uploadPath = '${config.uploadPath}';
	   restPath = '${config.restPath}';
	   templatePath = '${templatePath}';
	   login = ${sessionScope.login ? true : false};
	//-->
	</script>
</head>
<body>
<div id="content">
<div class="headNav">
    <c:forEach items="${modules['top_left']}" var="module">
    <div class="top_left">
        <jsp:include page="modules/${module.page}"></jsp:include>
    </div>
    </c:forEach>
    &nbsp;
	<c:forEach items="${modules['top_right']}" var="module">
	<div class="top_right">
        <jsp:include page="modules/${module.page}"></jsp:include>
	</div>
	</c:forEach>
</div>
<!-- content begins -->
<div class="clear"></div>
<div id="main">
	<div id="right">
        <div id="logo">         
            <img src="${templatePath}/images/banner.jpg" alt="first" width="700"/>
            <!-- Computer Science Team -->
        </div>
    	<div id="menu">
			<ul>
				<li id="button1"><a href="${scriptPath}">Trang chủ</a></li>
				<li id="button2"><a href="${scriptPath}?action=test.list" title="">Đề thi</a></li>
				<li id="button3"><a href="${scriptPath}?action=teststruct.list" title="">Cấu trúc đề</a></li>
				<li id="button4"><a href="${scriptPath}?action=question.list" title="">Câu hỏi</a></li>
				<li id="button4"><a href="${scriptPath}?action=topic.list" title="">Chủ đề</a></li>
				<li id="button5"><a href="${scriptPath}?action=user.list" title="Danh sách thành viên" target="_self">Thành viên</a></li>
				<li id="button6"><a href="http://code.google.com/p/ocwiki/" target="_blank" title="">Giới thiệu</a></li>
			</ul>
		</div>
    	<div id="righttop">
    	</div>
    	<div class="clear"></div>
		<div class="rightbg">
            <c:if test="${not empty sessionScope.user.warningMessage}">
                <div class="notification">Bạn bị cảnh cáo với lí do: 
                     ${sessionScope.user.warningMessage}  
                </div>
            </c:if>

			<!-- ########################################## -->
			<!--  nội dung action được đặt ở đây -->
			<!-- ########################################## -->
			<c:catch var="ex">
				<jsp:include page="actions/${action.descriptor.name}.jsp" />
			</c:catch>
			<c:choose>
                <c:when test="${empty ex}">
	                <c:forEach items="${modules['action-bottom']}" var="item">
	                   <c:set var="module" scope="request" value="${item}"></c:set>
	                    <h3>${module.title}</h3>
	                    <div class="article-bottom">
	                        <jsp:include page="modules/${module.page}"></jsp:include>
	                    </div>
	                </c:forEach>
                </c:when>
                <c:otherwise>
					<h3 style="color:red">${ex}</h3>
                </c:otherwise>			
			</c:choose>
		</div>			
				
	</div>
	<div id="left">		
		<div id="header"></div>
		<div id="lefttop"></div>
 
	    <c:forEach items="${modules['left']}" var="item">
	       <c:set var="module" scope="request" value="${item}"></c:set>
	        <h3>${module.title}</h3>
            <div class="leftbg">
                <jsp:include page="modules/${module.page}"></jsp:include>
            </div>
			<div class="leftcenter"></div>
	    </c:forEach>
		
		</div>
    	<div id="mainbot"></div>
		<!--content ends -->
	<!--footer begins -->
	</div>

    <c:forEach items="${modules['bottom']}" var="item">
       <c:set var="module" scope="request" value="${item}"></c:set>
       <jsp:include page="modules/${module.page}"></jsp:include>
    </c:forEach>

	<div id="footer">
		<p><a href="https://code.google.com/p/ocwiki/">ocwiki v0.1</a>. 
		Copyright © 2010. Powered by CS Force</p>
	</div>
</div>
<!-- footer ends -->


</body>
</html>