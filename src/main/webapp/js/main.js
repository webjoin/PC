Ext.onReady(function(){
	   var columns = [/*{header:'���',dataIndex:'id',name:'id'},
						{header:'���',dataIndex:'morningFee',name:'morningFee'
		   				,editor: {
		   	                xtype: 'numberfield',
		   	                allowBlank: false,
		   	                emptyText:0,
		   	                minValue: 0,
		   	                maxValue: 150000
			   	        },handler:function(){
			   	        	alert("2323");
			   	        }},
						{header:'�в�',dataIndex:'lunchFee',name:'lunchFee'},
						{header:'���',dataIndex:'supperFee',name:'supperFee'},
						{header:'��ͨ',dataIndex:'trafficFee',name:'trafficFee'},
						{header:'�ճ�',dataIndex:'everydayFee',name:'everydayFee'},
						{header:'С��',dataIndex:'snackFee',name:'snackFee'},
						{header:'����',dataIndex:'specialFee',name:'specialFee'},
						{header:'����',dataIndex:'totalFee',name:'totalFee',
							renderer: function(v, params, record){
								// ��ȡ�м�¼
								var obj = record.data;
								//�ܼ� 
								var total = obj.morningFee* obj.lunchFee *obj.supperFee*obj.trafficFee*obj.everydayFee*obj.snackFee*obj.specialFee
			                    return total;
			                }
						},
						{header:'ʱ��',dataIndex:'recordTime',name:'recordTime',width:120,
							renderer:Ext.util.Format.dateRenderer('Y-m-d'),
							editor:new Ext.form.DateField({
			                    format: 'Y-m-d'
			                })},
						{header:'����',dataIndex:'remark',name:'remark'},
						{header:'��ע',dataIndex:'note',name:'note'}*/
	                   {
	                       id: 'description',
	                       header: 'Task',
	                       width: 80,
	                       sortable: true,
	                       dataIndex: 'description',
	                       summaryType: 'count',
	                       hideable: false,
	                       summaryRenderer: function(v, params, data){
	                           return ((v === 0 || v > 1) ? '(' + v +' Tasks)' : '(1 Task)');
	                       },
	                       editor: new Ext.form.TextField({
	                          allowBlank: false
	                       })
	                   },{
	                       header: 'Project',
	                       width: 20,
	                       sortable: true,
	                       dataIndex: 'project'
	                   },{
	                       header: 'Due Date',
	                       width: 25,
	                       sortable: true,
	                       dataIndex: 'due',
	                       summaryType: 'max',
	                       renderer: Ext.util.Format.dateRenderer('m/d/Y'),
	                       editor: new Ext.form.DateField({
	                           format: 'm/d/Y'
	                       })
	                   },{
	                       header: 'Estimate',
	                       width: 20,
	                       sortable: true,
	                       dataIndex: 'estimate',
	                       summaryType: 'sum',
	                       renderer : function(v){
	                           return v +' hours';
	                       },
	                       editor: new Ext.form.NumberField({
	                          allowBlank: false,
	                          allowNegative: false,
	                          style: 'text-align:left'
	                       })
	                   },{
	                       header: 'Rate',
	                       width: 20,
	                       sortable: true,
	                       renderer: Ext.util.Format.usMoney,
	                       dataIndex: 'rate',
	                       summaryType: 'average',
	                       editor: new Ext.form.NumberField({
	                           allowBlank: false,
	                           allowNegative: false,
	                           style: 'text-align:left'
	                       })
	                   },{
	                       id: 'cost',
	                       header: 'Cost',
	                       width: 20,
	                       sortable: false,
	                       groupable: false,
	                       renderer: function(v, params, record){
	                           return Ext.util.Format.usMoney(record.data.estimate * record.data.rate);
	                       },
	                       dataIndex: 'cost',
	                       summaryType: 'totalCost',
	                       summaryRenderer: Ext.util.Format.usMoney
	                   }
	               ];
	   var Comsuption = [{name:'id'},{name:'morningFee'},{name:'lunchFee'},{name:'supperFee'},{name:'trafficFee'},
	                     {name:'everydayFee'},{name:'snackFee'},{name:'specialFee'},
						 {name:'totalFee'},{name:'totalFee'},{name:'recordTime'},{name:'remark'},
						 {name:'note'}];
	   var Obj = new Ext.data.Record.create(Comsuption);
	   var jsonData = {
			results: 2,  // Reader's configured totalProperty
			rows: [      // Reader's configured root
				{id:'0', morningFee:'0',lunchFee:'0',supperFee:'0', trafficFee:'0', 
					everydayFee:'0', snackFee:'0', specialFee:'0', totalFee:'0', totalFee:'0', 
					recordTime:new Date().toLocaleDateString(), remark:'A', note:'A'},
				{id:'1', morningFee:'0',lunchFee:'0',supperFee:'0', trafficFee:'0', 
					everydayFee:'0', snackFee:'0', specialFee:'0', totalFee:'0', totalFee:'0', 
					recordTime:new Date().toLocaleDateString(), remark:'A', note:'B'}
				
			]
	   };
	   var reader = new Ext.data.JsonReader({
	        idProperty: 'taskId',
	        fields: [
	            {name: 'projectId', type: 'int'},
	            {name: 'project', type: 'string'},
	            {name: 'taskId', type: 'int'},
	            {name: 'description', type: 'string'},
	            {name: 'estimate', type: 'float'},
	            {name: 'rate', type: 'float'},
	            {name: 'cost', type: 'float'},
	            {name: 'due', type: 'date', dateFormat:'m/d/Y'}
	        ]

	    });
	   var dummyData = [
                        {projectId: 100, project: 'Ext Forms: Field Anchoring', taskId: 112, description: 'Integrate 2.0 Forms with 2.0 Layouts', estimate: 6, rate: 150, due:'06/24/2007'}
                        ];
	   var store = new Ext.data.GroupingStore/*JsonStore*/({
			/*reader:new Ext.data.JsonReader({  // The metadata property, with configuration options:
					totalProperty: "counts", //   the property which contains the total dataset size (optional)
					root: "rows",             //   the property which contains an Array of record data objects
					idProperty: "id"          //   the property within each row object that provides an ID for the record (optional)
				},
				Obj                           // Ext.data.Record constructor that provides mapping for JSON object
			),
			// data:jsonData,
			proxy : new Ext.data.HttpProxy({
				url:'../jsp/main.jsp'
//				,method: 'GET'
			})
	   ,sortInfo:{field:'recordTime',direction:'ASC'}*/
		   reader: reader,
			// use local data
           data:dummyData,
//           sortInfo: {field: 'due', direction: 'ASC'},
//           groupField: 'project'
//		   app.grid.dummyData
	   });
	  
//	   var editor = new Ext.ux.grid.RowEditor({
//	        saveText: 'Update'
//	    });
	   Ext.ux.grid.GroupSummary.Calculations['totalFee'] = function(v, record, field){
		    var obj = record.data;
		    var total = obj.morningFee* obj.lunchFee *obj.supperFee*obj.trafficFee*obj.everydayFee*obj.snackFee*obj.specialFee
	        return total;
	    };
	   var summary = new Ext.ux.grid.GroupSummary();
	   
	   var grid = new Ext.grid.EditorGridPanel({
		   store : store,
		   columns: columns,
		   region:'center',
		   plugins: summary,
		   margins:'0 5 5 5',
		   
	        width: 800,
	        height: 450,
	        clicksToEdit: 1,
	        collapsible: true,
	        animCollapse: false,
	        trackMouseOver: false,
	        //enableColumnMove: false,
	        iconCls: 'icon-grid',
		   
		   view: new Ext.grid.GroupingView({
//	            forceFit: true,
//	            showGroupName: false,
//	            enableNoGroups: false,
//				enableGroupingMenu: false,
//	            hideGroupedColumn: true,
	            markDirty: false
	        })/*,
		   tbar:[{
			   text: ' ��  ��   ',
			   iconCls: 'icon-user-add',
	           handler: function(){
	                var e = new Obj({id:'0',
	        		    morningFee:'0',lunchFee:'0',supperFee:'0',
	        			trafficFee:'0',
	        			everydayFee:'0',
	        			snackFee:'0',
	        			specialFee:'0',
	        			totalFee:'0',
	        			totalFee:'0',
	        			recordTime:new Date(),
	        			remark:'A',
	        			note:'A'});
//	                editor.stopEditing();
	                grid.stopEditing();// ֹͣ�༭
	                store.insert(0, e);// ���뵽���һ��
	                grid.startEditing(0);// ��ʼ�༭1��Ԫ��
	                
//	                store.insert(0,e);
//	                grid.getView().refresh();
//	                grid.getSelectionModel().selectRow(0);
//	                editor.startEditing(0);
	           }
		   },{
			   text:'�h��',
			   ref: '../removeBtn',
			   iconCls: 'icon-user-delete',
			   disabled: true,
	           handler: function(){
//	                editor.stopEditing();
	                var s = grid.getSelectionModel().getSelections();
	                for(var i = 0, r; r = s[i]; i++){
	                    store.remove(r);
	                }
	            }
		   }]*/
	   });
	   var panel = new Ext.Panel({
		   title:'�ճ����Mӛ�',
		   height:600,
		   layout: 'border',
		   items:[grid],
		   renderTo:'grid'
	   });
	 })