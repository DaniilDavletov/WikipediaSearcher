<%@page import="clsses.WikiSearcher"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" media="all" href="css/searchViewCss.css">
        <title>Wiki searcher</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row">
            <div class="header text-center">
        	<span class="bigLetter">W</span>IKIPEDI<span class="bigLetter">A</span><span class="glyphicon glyphicon-search"></span>
            </div>
            <form name="queryForm" action="searchView.jsp" method="GET">
                <div class="input-group">
                    <input id="querry" class="form-control" type="text" placeholder="Search" name="query" value="" size="100" />
                    <div class="input-group-btn">
                        <button id="searchBtn" class="btn btn-default" type="submit" />
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                        <button id="getMessage" class="btn btn-default" type="button">
                            <a target="_blank" href="https://en.wikipedia.org/wiki/Special:Random">Random article</a>
                        </button>
                    </div>
                </div>           
            </form>
            <% 
                request.setCharacterEncoding("UTF-8");
                String query = request.getParameter("query"); 
                if(query == null)
                    return;
                if(query.isEmpty())
                    return;
                WikiSearcher searcher = new WikiSearcher("https://ru.wikipedia.org/w/api.php?action=opensearch&format=json&origin=*&search=", query);
            %>
            <%=searcher.getHTML()%>
        </div>
        <div class="row text-center myText"></div>
    </div>
</body>
</html>
