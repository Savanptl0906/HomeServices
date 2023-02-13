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
			public $name;
			public $mobile;
		}
		
		$i = $_REQUEST['providermobile'];		
		$e=array();
		$q = "select * from serviceman where provider_mobile = '$i'";
		$rs = $con->query($q);
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$e[$n]=new Request();
		
			$e[$n]->name = $r['name'];
			$e[$n]->mobile = $r['serviceman_mobile'];
			
			$n++;
		}
//		echo $q;
		$ans1 = json_encode($e );
		echo $ans1;	
	}
?>