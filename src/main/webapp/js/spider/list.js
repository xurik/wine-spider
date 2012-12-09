(function($){
    $(document).ready(function(){
        jQuery("#listList").jqGrid({
            mtype:'POST',
            colModel:[
                {name:'id',index:'id', lable:"ID",width:55,editable:true},
                {name:'uuid',index:'uuid', label:"唯一标识",width:120},
                {name:'uri',index:'uri', label:"地址",width:150,editable:true},

                {name:'gmtCreate',index:"gmtCreate",label:'创建时间', width:100},
                {name:'gmtModified',index:"gmtModified",label:'修改时间', width:80, align:"right"},
                {name:'creator',index:"creator",label:'创建人', width:80, align:"right"},
                {name:'modified',index:"modified",label:'最后修改人', width:80,align:"right"}
            ],
            rowNum:30,
            rowList:[30,50,100],
            pager: '#listPager',
            jsonReader: {
                repeatitems : false,
                id: "id"
            },
            height:500,
            sortname: 'id',
            viewrecords: true
        });
        jQuery("#listList").jqGrid('navGrid','#listPager',{edit:true,add:true,del:true});
    });
})(jQuery);
