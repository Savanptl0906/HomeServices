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
		

		
		$i = $_POST['providermobile'];
		$cname = $_POST['custname'];		
		//$q = "update request set status='completed' where providermobile = '$i'";
		//$p = "update recored set status='completed' where pr_mobile = '$i'";

		$q = "update request set status='completed' where  customername= '$cname'";
		$p = "update recored set status='completed' where cname = '$cname'";
		
		if($con->query($q) && $con->query($p))
		{
			echo "success";
		}
		else
		{
			echo "failed";
		}
	}
?>