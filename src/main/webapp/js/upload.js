	var fp = new Ext.FormPanel({
//	        renderTo: 'fi-form',
		 	region:'center',
	        fileUpload: true,
	        width: 500,
	        border:false,
	        frame: true,
	        autoHeight: true,
	        bodyStyle: 'padding: 10px 10px 0 10px;',
	        labelWidth: 80,
	        defaults: {
	            anchor: '90%',
	            allowBlank: false,
	            msgTarget: 'side'
	        },
	        items: [{
	            xtype: 'fileuploadfield',
	            id: 'form-file',
	            emptyText: '选择一个文件上传',
	            fieldLabel: '文件名称',
	            name: 'photo-path',
	            buttonText: '',
	            buttonCfg: {
	                iconCls: 'upload-icon'
	            }
	        }],
	        buttons: [{
	            text: '保存',
	            handler: function(){
	                if(fp.getForm().isValid()){
		                fp.getForm().submit({
		                    url: Util.Constant.RESOURCES_PATH+'record/batchImport',
		                    method:'POST',
		                    waitTitle:'请等待',
		                    waitMsg: '正在上传...',
		                    success: function(fp, o){
		                    	Ext.Msg.alert('提示','批量导入数据成功！');
		                    	fp.reset();
		                    	store.reload();
		                    },
		                    failure : function(form , action){
		                    	Ext.Msg.alert('提示','批量导入数据失败！');  
		                    }
		                });
	                }
	            }
	        },{
	            text: '重置',
	            handler: function(){
	                fp.getForm().reset();
	            }
	        },{
	            text: '下载事例文件',
	            handler: function(){
//	                fp.getForm().reset();
//	            	Ext.MessageBox.alert("提示", "文件下载成功！");
	            	var url = Util.Constant.RESOURCES_PATH+'record/donwloadExampleFile.txt';
//	            	var iframe = "<iframe src="+url+" />";
//	            	alert(Ext.getCmp('filePathPanel'));
	            	document.getElementById('downloadFile').src= url;
//	            	document.getElementById('downloadFile').src="";
	            }
	        }]
	    });
	var uploadWin = new Ext.Window({
		layout:'border',
		border:false,
		title: '文件上传', 
		width:550,
		height:125,
		closeAction:'hide',
		items:[fp]
	});
//	uploadWin.show();	
	
