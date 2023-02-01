

export function serializeObject(form) {
    var arrayData, objectData;
    arrayData = form.serializeArray();
    objectData = {};

    $.each(arrayData, function() {
        var value;

        if (this.value != null) {
            value = this.value;
        } else {
            value = '';
        }

        if (objectData[this.name] != null) {
            if (!objectData[this.name].push) {
                objectData[this.name] = [objectData[this.name]];
            }

            objectData[this.name].push(value);
        } else {
            objectData[this.name] = value;
        }
    });

    return objectData;
};

export function ResponseError(error){
    const errorData = error.response.data;
    let message = "";
    if(errorData.errors.length > 1){
        message = `[에러코드 : ${errorData.code}] ${errorData.errors[0].field}값이 ${errorData.errors[0].reason}`
    }
    else message = `[에러코드 : ${errorData.code}] ${errorData.message}`;
    return message;
}
