<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/includes/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${fn:escapeXml(pageTitle)}</title>
	
	<link rel="shortcut icon" href="${templatePath}/images/favicon.png">
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
<div id="headBar">&nbsp;</div>
<div id="content">
<div id="headNav">
    <div class="lfloat logo">
        <ocw:actionLink name="homepage">
            <img src="${homeDir}${config.logoPath}">
        </ocw:actionLink>
    </div>
    <div class="lfloat">
	    <c:forEach items="${modules['top_left']}" var="module">
	        <jsp:include page="modules/${module.page}"></jsp:include>
	    </c:forEach>
    </div>
    &nbsp;
    <ul class="rfloat">
		<c:forEach items="${modules['top_right']}" var="module">
			<li>
			        <jsp:include page="modules/${module.page}"></jsp:include>
			</li>
		</c:forEach>
	</ul>
</div>
<!-- content begins -->
<div class="clear"></div>
<div id="main">
	<div id="right">
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
                    <pre style="color:red">${ex}</pre>
                </c:otherwise>			
			</c:choose>
		</div>			
				
	</div>
	
	<div id="left">		
        <c:set var="i" value="0"></c:set>
	    <c:forEach items="${modules['left']}" var="item">
	        <h5 class="modtitle accor-header">${module.title}</h5>
	        <div id="leftmod${i}" class="accor-body">
		        <c:set var="module" scope="request" value="${item}"></c:set>
                <jsp:include page="modules/${module.page}"></jsp:include>
            </div>
            <script type="text/javascript">
			<!--
			new AccordionHandler('leftmod${i}');
			//-->
			</script>
            <c:set var="i" value="${i+1}"></c:set>
	    </c:forEach>
	</div>
	
   	<div id="mainbot">
	    <c:forEach items="${modules['bottom']}" var="item">
	       <c:set var="module" scope="request" value="${item}"></c:set>
	       <jsp:include page="modules/${module.page}"></jsp:include>
	    </c:forEach>
   	</div>
<!--content ends -->
</div>


<!--footer begins -->
<div id="footer">
    <div>
        <!-- Twitter & FB buttons -->
    
	    <!-- linkhay button -->
	    <script type="text/javascript">
		<!--
		var linkhay_title = '';
		var linkhay_url = location.href;
		var linkhay_desc = '';
		var linkhay_style = '2';
		//-->
		</script>
		<script type="text/javascript" src="http://linkhay.com/widgets/linkhay.js"></script>
    </div>
    <div class="rfloat">
		<a href="https://code.google.com/p/ocwiki/">ocwiki v0.1</a> 
		· 
		CS Force © 2010
		·
		<a href="#">Chúng tôi</a>
		·
		<a href="#">Trợ giúp</a>
	</div>
</div>
</div>
<!-- footer ends -->


</body>
</html>