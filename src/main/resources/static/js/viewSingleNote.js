function changeIsDoneMark() {
    $.get(location.href + '/changeIsDoneMark').then(function (json) {


    })
}

function viewNote() {
    $.get(location.href + '/updateSingleNote').then(function (noteView) {
        alert(noteView)
        if (noteView !== "") {
            $('#noteList').empty();
            var element = document.getElementById('noteDiv');
            for (let i = 0; i < element.length; i++) {
                element[i].remove();
            }
            var setNoteName, setNoteDescription, setNoteId, setNoteEndTime, setType, setIsDoneMark;
            var parsed = JSON.parse(noteView);
                setNoteName = parsed.noteName;
                setNoteDescription = parsed.noteDescription;
                setNoteId = parsed.noteId;
                setNoteEndTime = parsed.noteEndTime;
                setType = parsed.noteType;
                setIsDoneMark = parsed.noteIsDone;


                let block = document.createElement('div')
                block.className = 'blc'
                let naz = document.createElement('h2')
                naz.textContent = setNoteName
                var checkbox = document.createElement("input");
                checkbox.type = "checkbox";
                checkbox.id = setNoteId;
                if (setIsDoneMark.toString() === 'true'){
                    checkbox.checked = true;
                }

                checkbox.onclick = function (){
                    changeIsDoneMark();
                }

                let type = document.createElement('p')
                type.textContent = "Type: " + setType
                let opis = document.createElement('p')
                opis.textContent = "Description: " + setNoteDescription
                let endTime = document.createElement('p')
                endTime.textContent = "End time: " + setNoteEndTime

                block.appendChild(naz)
                block.appendChild(type)
                block.appendChild(opis)
                block.appendChild(endTime)
                block.appendChild(checkbox)
                element.appendChild(block)
        }
    });
}
$(document).ready(function () {
    viewNote();
})