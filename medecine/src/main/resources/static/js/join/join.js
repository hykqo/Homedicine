import axios from 'axios';
import {serializeObject, ResponseError} from "../utill/utill";
$(function (){
const form = $("#join_form");
const joinBtn = $('#join')
const config = {"Content-Type": 'application/json'};

joinBtn.on("click", function (){
        const data = serializeObject(form);
        const res =  axios.post("/user/join", data).catch(error => {
            alert(ResponseError(error));
        }).then(response =>{
            alert("회원가입이 완료되었습니다.");
            location.href = "/user/login";
        });
});
});