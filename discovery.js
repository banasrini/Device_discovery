(function (){
        var deviceList = $('#peeps'),
				users =[];

        var button = PUBNUB.$('dashboard');
        var pubnub = PUBNUB.init({
			  publish_key   : 'demo',
			  subscribe_key : 'demo',
			  uuid : 'human'
			})
            
        // sending a reply to the devices
        pubnub.bind('click', button, function () {
          pubnub.publish({
            channel: 'my_channel',
            presence: function(m){console.log(m)},
            message: $('#clientText').val()
          });
        });

        pubnub.subscribe({
          channel: 'my_channel',
          message: function(m){console.log(m)},
          presence: function(message,channel){
            if(message.action == "join"){
              users.push(message.uuid);
        			deviceList.append("<li style = 'text-align:center; font-size:150%'>" +"<b>"+ message.uuid + "</b>"+"</li>");
            }
            else{
          		users.splice(users.indexOf(message.uuid), 1);
          		deviceList.find(message.uuid).remove();
        		}	
          }
        });

        // subscribing to the channel that the user publishes to
        pubnub.subscribe({
			    channel : 'my_channel',
			    message : function(m){console.log(m)}	
			  });
            
        pubnub.here_now({
          channel : 'my_channel',
          callback : function(m){console.log(JSON.stringify(m))}
        });
})();
