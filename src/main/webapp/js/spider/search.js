function list(id){
    $("#searchId").val(id);
    $('#listList').jqGrid('setGridParam',{
        url:'/list/'+id+'/list.j',
        editurl:'/list/'+id+'/save.j',
        datatype: "json"
    }).trigger('reloadGrid');
    $( "#tabs" ).tabs("select","tabs-3");

}
(function($){
    $(document).ready(function(){
        function formatterList(cellvalue, options, rowObject){
            var result = '<button onclick="list('+rowObject.id+')">查看列表</button>';
            return result;
        }
        jQuery("#searchList").jqGrid({
            mtype:'POST',
            datatype: "local",
            colModel:[
                {name:'id',index:'id', lable:"ID",width:55,editable:true},
                {name:'uuid',index:'uuid', label:"唯一标识",width:120},
                {name:'uri',index:'uri', label:"地址",width:150,editable:true},

                {name:'gmtCreate',index:"gmtCreate",label:'创建时间', width:100},
                {name:'gmtModified',index:"gmtModified",label:'修改时间', width:80, align:"right"},
                {name:'creator',index:"creator",label:'创建人', width:80, align:"right"},
                {name:'modified',index:"modified",label:'最后修改人', width:80,align:"right"} ,
                {name:'listEntityList',index:"id",label:'操作', width:80,align:"right",formatter:formatterList}
            ],
            rowNum:30,
            rowList:[30,50,100],
            pager: '#searchPager',
            jsonReader: {
                repeatitems : false,
                id: "id"
            },
            height:500,
            sortname: 'id',
            viewrecords: true
        });
        jQuery("#searchList").jqGrid('navGrid','#searchPager',{edit:true,add:true,del:true});
    });
})(jQuery);
