/**
 * Created by Jonsy
 */
$.ajaxSetup({headers:{'X-CSRF-TOKEN':$("#csrf_token").attr("content")}});

