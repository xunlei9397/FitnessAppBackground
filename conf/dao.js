var ioc = {
        "defaultDao" : {
                type : "org.nutz.dao.impl.NutDao",
                args : [{refer:"defaultDataSource"}]
        },
        defaultDataSource : {
    		type : "com.mchange.v2.c3p0.ComboPooledDataSource",
    		events : {
    			depose : 'close'
    		},
    		fields : {
    		
    			driverClass : 'com.mysql.jdbc.Driver',  //虚拟机的oracle
    			jdbcUrl : 'jdbc:mysql://localhost:3306/fit?useUnicode=true&amp;characterEncoding=UTF-8',
    			user : 'root',
    			password : 'root',
    		
    		}
    	}
    	
}	
