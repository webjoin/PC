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
	            emptyText: 'ѡ��һ���ļ��ϴ�',
	            fieldLabel: '�ļ�����',
	            name: 'photo-path',
	            buttonText: '',
	            buttonCfg: {
	                iconCls: 'upload-icon'
	            }
	        }],
	        buttons: [{
	            text: '����',
	            handler: function(){
	                if(fp.getForm().isValid()){
		                fp.getForm().submit({
		                    url: Util.Constant.RESOURCES_PATH+'record/batchImport',
		                    method:'POST',
		                    waitTitle:'��ȴ�',
		                    waitMsg: '�����ϴ�...',
		                    success: function(fp, o){
		                    	Ext.Msg.alert('��ʾ','�����������ݳɹ���');
		                    	fp.reset();
		                    	store.reload();
		                    },
		                    failure : function(form , action){
		                    	Ext.Msg.alert('��ʾ','������������ʧ�ܣ�');  
		                    }
		                });
	                }
	            }
	        },{
	            text: '����',
	            handler: function(){
	                fp.getForm().reset();
	            }
	        },{
	            text: '���������ļ�',
	            handler: function(){
//	                fp.getForm().reset();
//	            	Ext.MessageBox.alert("��ʾ", "�ļ����سɹ���");
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
		title: '�ļ��ϴ�', 
		width:550,
		height:125,
		closeAction:'hide',
		items:[fp]
	});
//	uploadWin.show();	
	
