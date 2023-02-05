import axios from "axios";
import {serializeObject, ResponseError} from "../utill/utill";

$(function (){
    //form
    const findIdForm = $('#find_id_form'), findPwForm = $('#find_pw_form');
    //button
    const findIdBtn = $('#find_id'), findPwBtn = $('#find_pw'),  updatePwBtn = $("#update_pw");

    findIdBtn.on('click', async function (){
       const data = serializeObject(findIdForm);
       const res = axios.post("/user/find/id", data).then(response => {
               if(confirm(`아이디는 ${response.data.id} 입니다.\n로그인 가러 가시겠어요?`)) location.href = "/user/login";
           }).catch(error => {
               alert(ResponseError(error));
           });
    });

    findPwBtn.on('click', async function (){
        const data = serializeObject(findPwForm);
        const res = axios.post("/user/find/pw", data).then(() => {
                $("#name").prop("type", "hidden");
                $("#id").prop("type", "hidden");
                $("#phone").prop("type", "hidden");
                findPwBtn.hide();
                $("#pw").prop("type", "password");
                updatePwBtn.show();
        }).catch(error => {
            alert(ResponseError(error));
        });
    });

    updatePwBtn.on('click', async function (){
        const data = serializeObject(findPwForm);
        const res = axios.post("/user/update/pw", data).then(() => {
                if(confirm("비밀번호 변경이 완료되었습니다.\n로그인 하러 가시겠어요?")) location.href = "/user/login";
            }).catch(error => {
            alert(ResponseError(error));
        });
    });




});