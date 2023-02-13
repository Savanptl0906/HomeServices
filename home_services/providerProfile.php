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
			public $email;
			public $address;
			public $pincode;
			public $category;
			public $visitcharge;
		}
		$i = $_REQUEST['providermobile'];		
		$e=array();
		$q = "select * from provider where mobile = '$i'";
		$rs = $con->query($q);
		$n=0;	
		
		while($r = mysqli_fetch_assoc($rs))
		{	
			$e[$n]=new Provider();
			
			$e[$n]->shopname = $r['shopname'];
			$e[$n]->name = $r['name'];
			$e[$n]->email = $r['email'];
			$e[$n]->address = $r['address'];
			$e[$n]->pincode =$r['pincode'];	
			$e[$n]->category = $r['category'];
			$e[$n]->visitcharge = $r['visitcharge'];
			$n++;
		}
//		echo $q;
		$ans1 = json_encode($e);
		echo $ans1;	
	}
?>