<!DOCTYPE html>
<html>
<head>
    <meta charset="GBK" />
    <link rel="stylesheet" href="/css/jquery-ui/smoothness/jquery-ui-1.9.2.custom.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/css/jqgrid/ui.jqgrid.css" type="text/css" media="screen" />
    <script type="text/javascript" src="/js/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/js/jquery/jquery-ui/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript" src="/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>

    <title>demo</title>
</head>
<body>
<table id="list2"></table>
<div id="pager2"></div>
</body>
</html>
<script>
(function($){//防止$变量冲突
    $(document).ready(function(){
        jQuery("#list2").jqGrid({
            url:'server.php?q=2',
            datatype: "json",
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'invdate',index:'invdate', width:90},
                {name:'name',index:'name asc, invdate', width:100},
                {name:'amount',index:'amount', width:80, align:"right"},
                {name:'tax',index:'tax', width:80, align:"right"},
                {name:'total',index:'total', width:80,align:"right"},
                {name:'note',index:'note', width:150, sortable:false}
            ],
            rowNum:10,
            rowList:[10,20,30],
            pager: '#pager2',
            sortname: 'id',
            viewrecords: true,
            sortorder: "desc"
        });
        jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
    });
})(jQuery);
</script>

