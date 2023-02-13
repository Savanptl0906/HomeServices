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
		class Ratting
		{
			public $rate;
		}
		$i = $_REQUEST['pr_mobile'];		
		$e=array();
		$q = "select avg(rate) from ratting where pr_mobile = '$i'";
		$rs = $con->query($q);
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$ans1 = $r["rate"];
		}
//		echo $q;
		echo $ans1;	
	}
?>