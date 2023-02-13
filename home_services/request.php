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
//		echo "Connection done";

		$providershop=$_POST['providershop'];
		$providername=$_POST['providername'];
		$providermobile=$_POST['providermobile'];
		$provideraddress=$_POST['provideraddress'];
		$providercharge=$_POST['providercharge'];
		$customername=$_POST['customername'];
		$customermobile=$_POST['customermobile'];
		$customeraddress=$_POST['customeraddress'];
		$model=$_POST['model'];
		$issue=$_POST['issue'];
		
		$status="pending";
		
		
		
		$q="insert into request values('$providershop','$providername','$providermobile','$provideraddress','$providercharge','$customername','$customermobile','$customeraddress','$model','$issue','$status')";
		
		$q1 = "select * from provider_token where mobile='$providermobile'";
		
		
		echo $q1;
		if($con->query($q))
		{
			echo "insertion done";
			  $rs=$con->query($q1);
			if($r = mysqli_fetch_assoc($rs))
			{
			$token = $r['token'];
			echo $token;
			send_notification($token,$issue,$customername);
			}	
		}
		else
		{
			echo "insertion failed";
		}
	}
	function send_notification($token,$issue,$customername)
{
	define( 'API_ACCESS_KEY', 'AAAAeRS_C4k:APA91bH1ic3hV8D7siiv4w5nWH0Xo-YMn1_IfDU9z5mPaP7TJMuX2s2FuZpq0xug9s3FF3dpYf7GF97Y8wgkN9bv7t4zfH33v5GFQ9t2Mftjfxrn-UwaKdO1FQJ9MhKUm9ey72pPa7Fq');

     $msg = array
          (
		'body' 	=> "Customer's issue : $issue.", 
		'title'	=> "New Service Request from $customername.",
		'click_action' => 'OPEN_ACTIVITY_1',
          );

          $data = array
          (
          	
          	'body' 	=> "Customer's issue : $issue.",
			'title'	=> "New Service Request from $customername.",
			'click_action' => 'OPEN_ACTIVITY_1',
          );

	$fields = array
			(
				'to'		=> $token,
				'data'	=> $data,
				'notification'	=> $msg,
            );
  $headers = array
			(
				'Authorization: key=' . API_ACCESS_KEY,
				'Content-Type: application/json'
			);

		$ch = curl_init();
		curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
		curl_setopt( $ch,CURLOPT_POST, true );
		curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
		curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
		curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
		curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
		$result = curl_exec($ch );
		echo $result;
		curl_close( $ch );

}
?>