Ext.onReady(function(){
	
Ext.QuickTips.init();

    var xg = Ext.grid;
    var columns = [new Ext.grid.RowNumberer(),{header:'���',dataIndex:'id',name:'id',hidden:true},
					{header:'���',dataIndex:'morningFee',name:'morningFee',
					summaryType: 'sum',
	   				editor: {
	   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
		   	        },handler:function(){
		   	        	alert("2323");
		   	        }},
					{header:'�в�',dataIndex:'lunchFee',name:'lunchFee',summaryType: 'sum',
		   				editor: {
		   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
			   	        }},
					{header:'���',dataIndex:'supperFee',name:'supperFee',summaryType: 'sum',
		   				editor: {
		   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
			   	        }},
					{header:'��ͨ',dataIndex:'trafficFee',name:'trafficFee',summaryType: 'sum',
		   				editor: {
		   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
			   	        }},
					{header:'�ճ�',dataIndex:'everydayFee',name:'everydayFee',summaryType: 'sum',
		   				editor: {
		   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
			   	        }},
					{header:'С��',dataIndex:'snackFee',name:'snackFee',summaryType: 'sum',
		   				editor: {
		   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
			   	        }},
					{header:'����',dataIndex:'specialFee',name:'specialFee',summaryType: 'sum',
		   				editor: {
		   	                xtype: 'numberfield',allowBlank: false,emptyText:0,minValue: 0,maxValue: 150000
			   	        }},
					{header:'����',dataIndex:'totalFee',name:'totalFee',
						summaryType: 'totalFee1',
						renderer: function(v, params, record){
							// ��ȡ�м�¼
							var obj = record.data;
							//�ܼ� 
							var total = parseFloat(obj.morningFee) + parseFloat(obj.lunchFee) + parseFloat(obj.supperFee) + parseFloat(obj.trafficFee) + parseFloat(obj.everydayFee) + parseFloat(obj.snackFee) + parseFloat(obj.specialFee)
//							alert(obj.morningFee + obj.lunchFee)
		                    return total;
		                }/*,
		                summaryRenderer: Ext.util.Format.usMoney*/
					},
					{header:'ʱ��',dataIndex:'recordTime',name:'recordTime',width:120,
						summaryType: 'max',
						renderer:Ext.util.Format.dateRenderer('Y-m-d'),
						editor: {
		   	                xtype: 'datefield',allowBlank: false,
		   	                format:'Y-m-d'
			   	        }},
					{header:'����',dataIndex:'remark',name:'remark',summaryType: 'count',
		                editor: {
	   	                	xtype: 'textfield',allowBlank: false,emptyText:'A'
		   	        	}
		            },{
		            header:'��ע',dataIndex:'note',name:'note',summaryType: 'count',
		   	        	editor: {
		   	        		xtype: 'textfield',allowBlank: false,emptyText:'A'
		   	        	}
		   	        },
		   	        {header:'��������',dataIndex:'groupByDate',name:'groupByDate',summaryType:'max',
		   	        	hidden:true
		   	        }
					];
    var Comsuption = [{name:'id'},{name:'morningFee'},{name:'lunchFee'},{name:'supperFee'},{name:'trafficFee'},
	                     {name:'everydayFee'},{name:'snackFee'},{name:'specialFee'},
						 {name:'totalFee'},{name:'totalFee'},{name:'recordTime'},{name:'remark'},
						 {name:'note'},{name:'groupByDate'}];
    var Obj = new Ext.data.Record.create(Comsuption);
    var reader = new Ext.data.JsonReader({
//        idProperty: 'taskId',
        fields: Comsuption,
        totalProperty:'count',
    	root: 'rows'
    });

    // define a custom summary function
    Ext.ux.grid.GroupSummary.Calculations['totalFee1'] = function(v, record, field){
    	// ��ȡ�м�¼
//		var obj = record.data;
		//�ܼ� 
//		var total = v + (obj.lunchFee + obj.supperFee + obj.trafficFee + obj.everydayFee + obj.snackFee + obj.specialFee)
//        return total;
		
    };
//    queryAll
	// utilize custom extension for Group Summary
    var summary = new Ext.ux.grid.GroupSummary();
    var store = new Ext.data.GroupingStore({
//    	 use local data
//       data: app.grid.dummyData,
    	reader: reader,
    	proxy:new Ext.data.HttpProxy({
    		url:Util.Constant.RESOURCES_PATH+'record/queryAll',
            method: "GET"
    	}),
    	autoLoad:true,
    	fields:columns,
        sortInfo: {field: 'recordTime', direction: 'ASC'},
        groupField: 'groupByDate'
    }); 
//    û�ж�ѡ��
//    var sm = new Ext.grid.RowSelectionModel({singleSelect:false});
    var grid = new xg.EditorGridPanel({
        store: store,
        columns: columns,
//        sm:sm,
        view: new Ext.grid.GroupingView({
            forceFit: true
            /*,
            showGroupName: false,
            enableNoGroups: false,
			enableGroupingMenu: false,
            hideGroupedColumn: true*/
        }),

        plugins: summary,

        tbar : [{
        	iconCls:'addRecord',
            text: ' ͳ �� ',
            tooltip: 'ͳ��ÿ�������',
            handler: function(){summary.toggleSummaries();}
        },{
        	text:' ���� ',
        	iconCls:'addRecord',
        	handler:function(){
        		var count = store.getCount();
        		var e = new Obj({
        			id:0,morningFee:0,lunchFee:0,supperFee:0,
        			trafficFee:0,everydayFee:0,snackFee:0,
        			specialFee:0,totalFee:0,totalFee:0,
        			recordTime:new Date().format('Y-m-d'),remark:'A',note:'A',
        			groupByDate:''
        		});
        		
//        		this.stopEditing();
                e.isAddedRecord = true;
                e.markDirty();
                store.insert(count,e);
//                this.startEditing(count, 1);
//                this.getSelectionModel().selectRow(count);
        	}
        },{
        	iconCls:'addRecord',
        	text:'����',
        	handler:function(){
        		
        		var json = {
                    add : [],
                    update : []
                };
        		// ��ȡ���޸ĵ���
        		var selectedRecords = store.getModifiedRecords();
//        		var selectedRecords = this.getSelectionModel().getSelections();
        		// ��ȡѡ�е���
        		if (selectedRecords.length == 0) {
        			Ext.MessageBox.alert("��ʾ", "û�����ݱ��������޸�");
                    return;
                }
        		for (var i = 0; i < selectedRecords.length; i++) {
        			var rec = selectedRecords[i];
        			if (rec.dirty) {
//        				totalFee ��ȡ��ֵ��ô�ǿյ�
//        				alert(rec.get('totalFee'));
        				if (rec.get('totalFee') == 0 ) {
        					Ext.MessageBox.alert("��ʾ", rec.get("recordTime")/*.toLocateDateString()*/ +"��������Ϊ0 Ԫ��Ҫ������");
        				}
        				var totalFee = (rec.get('morningFee')+rec.get('lunchFee')+rec.get('supperFee')+rec.get('trafficFee')+rec.get('everydayFee')+rec.get('snackFee')+rec.get('specialFee'))
        				var data = {
        						id:rec.get('id'), 
        						morningFee:rec.get('morningFee'),
        						lunchFee:rec.get('lunchFee'),
        						supperFee:rec.get('supperFee'),
        						trafficFee:rec.get('trafficFee'),
        						everydayFee:rec.get('everydayFee'),
        						snackFee:rec.get('snackFee'),
        						specialFee:rec.get('specialFee'),
        						totalFee:totalFee,
        						recordTime:new Date(rec.get('recordTime')).format('Y-m-d'),
        						remark:rec.get('remark'),
        						note:rec.get('note'),
        						groupByDate:new Date(rec.get('recordTime')).format('Y-m')
        				};
        				if (rec.isAddedRecord) {
        					json.add.push(data);
        				} else {
        					json.update.push(data);
        				}
        			}
        		}
                if (json.add.length == 0 && json.update.length == 0) {
                    Ext.MessageBox.alert("��ʾ", "û�����ݱ��������޸�");
                    return;
                }
//                alert("���   "+json.add[0].totalFee);
                Ext.Msg.confirm('��ʾ', 'ȷ��Ҫ������', function(btn, text){
	                if (btn == 'yes'){
	                    Ext.Ajax.request({
	                                url :  Util.Constant.RESOURCES_PATH + 'record/batchUpdateOrSave',
	                                method : 'POST',
	                                jsonData : json,
	                                disableCaching : true,
	                                success : function(response, options) {
//	                                    this.getSelectionModel().clearSelections();
//	                                    this.getStore().load({params:{start:this.startPos,limit:10}});
//	                                    DelayMessage.show('ϵͳ��Ϣ', "����ɹ� !");
	                                	Ext.Msg.alert("ϵͳ��Ϣ", "����ɹ� !");
	                                    //store.load();
//	                                	refresh û��Ч��
//	                                    grid.getView().refresh();
	                                	store.reload();
	                                    
	                                },
	                                failure : function(response, options) {
	                                    Ext.Msg.alert("ϵͳ��Ϣ", response.responseText);
	                                },
	                                scope : this
	                            })
	                }
	            }, this);
        	}
        },{
        	text:'����',
        	iconCls:'addRecord',
        	handler:function(){
        		uploadWin.show();
        	}
        }],
//        height: 600,
        clicksToEdit: 1,
        margins:"0 5 5 5",
        collapsible: true,
        animCollapse: false,
        trackMouseOver: false,
        enableColumnMove: false,
        region:'center'
       
    });
//    store.load({params:{start:0, limit:store}});
    
    var body = new Ext.Panel({
    	 title:'�ճ����Ѽ�¼ϵͳ',
    	 height: 600,
    	 layout: 'border',
    	 items:[grid],
    	 iconCls: 'gridIcon',
    	 renderTo: 'grid'
    });

});
// set up namespace for application
Ext.ns('app.grid');
// store dummy data in the app namespace
app.grid.dummyData = [      // Readers configured root
            				{id:0, morningFee:4,lunchFee:10,supperFee:0, trafficFee:0, 
            					everydayFee:0, snackFee:0, specialFee:0, totalFee:0, 
            					recordTime:new Date(), remark:'A', note:'A',groupByDate:'2012-01'},
            					
            				{id:1, morningFee:3.5,lunchFee:10,supperFee:0, trafficFee:0, 
            					everydayFee:0, snackFee:0, specialFee:0, totalFee:0, 
            					recordTime:new Date(), remark:'A', note:'B',groupByDate:'2013-01'}
            				
            			];
