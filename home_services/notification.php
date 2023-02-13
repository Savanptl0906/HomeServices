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

	
//	$mobile = $_POST['mobile'];
	
	$q = "select * from customer_tokens where mobile='$mobile'";
	
		$rs = $con->query($q);
		
		if($r = mysqli_fetch_assoc($rs))
		{
			$token = $r['token'];
			echo $token;
			send_notification($token,'this is my msg');
		}	
	}
function send_notification($token,$msg)
{
	define( 'API_ACCESS_KEY', 'AAAAeRS_C4k:APA91bH1ic3hV8D7siiv4w5nWH0Xo-YMn1_IfDU9z5mPaP7TJMuX2s2FuZpq0xug9s3FF3dpYf7GF97Y8wgkN9bv7t4zfH33v5GFQ9t2Mftjfxrn-UwaKdO1FQJ9MhKUm9ey72pPa7Fq');

     $msg = array
          (
		'body' 	=> $msg,
		'title'	=> 'title text here',
		'click_action' => 'OPEN_ACTIVITY_1',
          );

          $data = array
          (
          	
          	'body' 	=> $msg,
			'title'	=> 'title text here',
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