<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK" />
    <link rel="stylesheet" href="/css/jquery-ui/smoothness/jquery-ui-1.9.2.custom.min.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/css/jqgrid/ui.jqgrid.css" type="text/css" media="screen" />
    <script type="text/javascript" src="/js/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript" src="/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="/js/spider/site.js"></script>
    <title>demo</title>
</head>
<body>
<div>
    <form id="siteQueryForm">
        <table>
            <tbody>
            <tr>
                <th>中文名</th>
                <td><input name="name" /></td>
                <th>站点</th>
                <td><input name="site" /></td>
                <th>状态</th>
                <td><input name="status" /></td>
                <td>
                    <button>查询</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>

</div>
<div>
    <table id="list2"></table>
    <div id="pager2"></div>
</div>

</body>
</html>
