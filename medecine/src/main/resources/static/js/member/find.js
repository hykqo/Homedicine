import axios from "axios";
import {serializeObject, ResponseError} from "../utill/utill";

$(function (){
    //form
    const findIdForm = $('#find_id_form'), findPwForm = $('#find_pw_form');
    //button
    const findIdBtn = $('#find_id'), findPwBtn = $('#find_pw');

    findIdBtn.on('click', async function (){
       const data = serializeObject(findIdForm);
       const res = axios.post("/")
    });





});