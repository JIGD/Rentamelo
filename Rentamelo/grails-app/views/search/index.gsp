<%@ page import="org.springframework.util.ClassUtils" %>
<%@ page import="grails.plugin.searchable.internal.lucene.LuceneUtils" %>
<%@ page import="grails.plugin.searchable.internal.util.StringQueryUtils" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title><g:if test="${params.q && params.q?.trim() != ''}">${params.q} - </g:if>Busqueda de Articulos:</title>    
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    
    
    <script type="text/javascript">
        var focusQueryInput = function() {
            document.getElementById("q").focus();
        }
    </script>
  	  <link href="${resource(dir: 'css', file: 'app.css')}" type="text/css" rel="stylesheet">
  </head>
  <body onload="focusQueryInput();">
  <div id="header">
    <h2>Busqueda de articulos</h2>
    <g:form url='[controller: "search", action: "index"]' id="searchableForm" name="searchableForm" method="get">
        <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
    </g:form>
    <div style="clear: both; display: none;" class="hint">See <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for advanced queries</div>
  </div>
  <div id="main">
    <g:set var="haveQuery" value="${params.q?.trim()}" />
    <g:set var="haveResults" value="${searchResult?.results}" />
    <div class="title">
      <span>
        <g:if test="${haveQuery && haveResults}">
          Mostrando <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
          resultados para <strong>${params.q}</strong>
        </g:if>
        <g:else>
        &nbsp;
        </g:else>
      </span>
    </div>

    <g:if test="${haveQuery && !haveResults && !parseException}">
      <p>Nada concuerda con tu busqueda - <strong>${params.q}</strong></p>
      <g:if test="${!searchResult?.suggestedQuery}">
        <p>Suggestions:</p>
        <ul>
          <li>Try a suggested query: <g:link controller="search" action="index" params="[q: params.q, suggestQuery: true]">Search again with the <strong>suggestQuery</strong> option</g:link><br />
            <em>Note: Suggestions are only available when classes are mapped with <strong>spellCheck</strong> options, either at the class or property level.<br />
		The simplest way to do this is add <strong>spellCheck "include"</strong> to the domain class searchable mapping closure.<br />
                See the plugin/Compass documentation Mapping sections for details.</em>
          </li>
        </ul>
      </g:if>
    </g:if>

    <g:if test="${searchResult?.suggestedQuery}">
      <p>Quisiste decir <g:link controller="search" action="index" params="[q: searchResult.suggestedQuery]">${StringQueryUtils.highlightTermDiffs(params.q.trim(), searchResult.suggestedQuery)}</g:link>?</p>
    </g:if>

    <g:if test="${parseException}">
      <p>Tu busqueda - <strong>${params.q}</strong> - no es valida.</p>
      <p>Suggestions:</p>
      <ul>
        <li>Fix the query: see <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for examples</li>
        <g:if test="${LuceneUtils.queryHasSpecialCharacters(params.q)}">
          <li>Remove special characters like <strong>" - [ ]</strong>, before searching, eg, <em><strong>${LuceneUtils.cleanQuery(params.q)}</strong></em><br />
              <em>Use the Searchable Plugin's <strong>LuceneUtils#cleanQuery</strong> helper method for this: <g:link controller="search" action="index" params="[q: LuceneUtils.cleanQuery(params.q)]">Search again with special characters removed</g:link></em>
          </li>
          <li>Escape special characters like <strong>" - [ ]</strong> with <strong>\</strong>, eg, <em><strong>${LuceneUtils.escapeQuery(params.q)}</strong></em><br />
              <em>Use the Searchable Plugin's <strong>LuceneUtils#escapeQuery</strong> helper method for this: <g:link controller="search" action="index" params="[q: LuceneUtils.escapeQuery(params.q)]">Search again with special characters escaped</g:link></em><br />
              <em>Or use the Searchable Plugin's <strong>escape</strong> option: <g:link controller="search" action="index" params="[q: params.q, escape: true]">Search again with the <strong>escape</strong> option enabled</g:link></em>
          </li>
        </g:if>
      </ul>
    </g:if>

    <g:if test="${haveResults}">
      <div class="results">
      
      <g:render template="/item/itemCard" collection="${searchResult.results}" var="item"/>
     
     
       
      </div>

      <div>
        <div class="paging">
          <g:if test="${haveResults}">
              Page:
              <g:set var="totalPages" value="${Math.ceil(searchResult.total / searchResult.max)}" />
              <g:if test="${totalPages == 1}"><span class="currentStep">1</span></g:if>
              <g:else><g:paginate controller="search" action="index" params="[q: params.q]" total="${searchResult.total}" prev="&lt; previous" next="next &gt;"/></g:else>
          </g:if>
        </div>
      </div>
    </g:if>
  </div>
  </body>
</html>
