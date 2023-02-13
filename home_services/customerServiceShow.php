<?php	
	$sname="localhost";
	$uname="root";
	$psd="";
	$dbname="home_services";
	$con=new mysqli($sname,$uname,$psd,$dbname);
	if($con->connect_error)
	{
		die("Connection error");
	}
	else
	{
	//	echo "connecion done...";
		class Request
		{
			public $cname;
			public $cmobile;
			public $caddress;
			public $model;
			public $issue;
			public $visitcharge;
	
			public $pr_name;
			public $pr_mobile;
			
			public $sname;
			public $smobile;

		}
		
		$i = $_REQUEST['cmobile'];		
		$e=array();
		$q = "select * from recored where cmobile = '$i' and status='pending'";
		$rs = $con->query($q);
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$e[$n]=new Request();
		
			$e[$n]->cname = $r['cname'];
			$e[$n]->cmobile = $r['cmobile'];
			$e[$n]->caddress = $r['caddress'];
			$e[$n]->model = $r['model'];
			$e[$n]->issue = $r['issue'];			
			$e[$n]->visitcharge = $r['visitcharge'];
			
			$e[$n]->pr_name = $r['pr_name'];			
			$e[$n]->pr_mobile = $r['pr_mobile'];
			
			$e[$n]->sname = $r['sname'];
			$e[$n]->smobile = $r['smobile'];
			
			
			$n++;
		}
//		echo $q;
		$ans1 = json_encode($e );
		echo $ans1;	
	}
?>