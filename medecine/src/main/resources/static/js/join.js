import axios from 'axios';

const form = $("#join_form");
const joinBtn = $('#join')
joinBtn.on("click", function (){
        const data = form.serialize();
        console.log(data);
        const res = axios.post("/user/join", data, {"Content-Type": 'application/json'})
            .catch(error => {
                alert("회원가입에 실패했습니다. : " + error.response);
            });
        if(res){
            alert("회원가입에 성공했습니다.");
        }
});
