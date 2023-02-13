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
		class Provider
		{
			public $shopname;
			public $name;
			public $mobile;		
			public $address;		
			public $visitcharge;

			//public $ratting;
		}
		$i = $_REQUEST['category'];		
		$e=array();
		$q = "select * from provider where category = '$i'";
		
		$rs = $con->query($q);
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$e[$n]=new Provider();
			
			$e[$n]->shopname = $r['shopname'];
			$e[$n]->mobile =$r['mobile'];	
			$e[$n]->address =$r['address'];	
			$e[$n]->visitcharge =$r['visitcharge'];	
			$e[$n]->name =$r['name'];	
			$n++;
		}
		
		/*$q1 = "select avg(rate) from ratting where pr_mobile='$e[1]'";
		$rs1 = $con->query($q1);
		array_push($e,$rs1);*/
//		echo $q;
		$ans1 = json_encode($e );
		echo $ans1;	
	}
?>