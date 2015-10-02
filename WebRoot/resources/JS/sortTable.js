(function($){  
    //鎻掍欢  
    $.extend($,{  
        //鍛藉悕绌洪棿  
        sortTable:{  
            sort:function(tableId,Idx){  
                var table = document.getElementById(tableId);  
                var tbody = table.tBodies[0];  
                var tr = tbody.rows;   
          
                var trValue = new Array();  
                for (var i=0; i<tr.length; i++ ) {  
                    trValue[i] = tr[i];  //灏嗚〃鏍间腑鍚勮鐨勪俊鎭瓨鍌ㄥ湪鏂板缓鐨勬暟缁勪腑  
                }  
          
                if (tbody.sortCol == Idx) {  
                    trValue.reverse(); //濡傛灉璇ュ垪宸茬粡杩涜鎺掑簭杩囦簡锛屽垯鐩存帴瀵瑰叾鍙嶅簭鎺掑垪  
                } else {  
                    //trValue.sort(compareTrs(Idx));  //杩涜鎺掑簭  
                    trValue.sort(function(tr1, tr2){  
                        var value1 = tr1.cells[Idx].innerHTML;  
                        var value2 = tr2.cells[Idx].innerHTML;  
                        return value1.localeCompare(value2);  
                    });  
                }  
          
                var fragment = document.createDocumentFragment();  //鏂板缓涓�釜浠ｇ爜鐗囨锛岀敤浜庝繚瀛樻帓搴忓悗鐨勭粨鏋� 
                for (var i=0; i<trValue.length; i++ ) {  
                    fragment.appendChild(trValue[i]);  
                }  
          
                tbody.appendChild(fragment); //灏嗘帓搴忕殑缁撴灉鏇挎崲鎺変箣鍓嶇殑鍊� 
                tbody.sortCol = Idx;  
                
                // 重新标号
                var oTable = document.getElementById("bbsTab");
				for(var i=0;i<oTable.rows.length;i++){
    				oTable.rows[i].cells[0].innerHTML = (i+1);
				}
                
                
            }  
        }  
    });         
})(jQuery);  
