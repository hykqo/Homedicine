import axios from 'axios';
import {serializeObject, ResponseError} from "../utill/utill";
$(function (){
const joinForm = $("#join_form");
const joinBtn = $('#join')

joinBtn.on("click", function (){
        const data = serializeObject(joinForm);
        const res =  axios.post("/user/join", data).then(response =>{
                alert("회원가입이 완료되었습니다.");
                location.href = "/user/login";
            }).catch(error => {
            alert(ResponseError(error));
        });
});
});