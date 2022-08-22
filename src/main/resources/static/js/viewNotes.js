function clickNote(data) {
    $.get(location.href + "/" + data.id + '/watchNote').then(function (dataNote) {
        if (dataNote[0] === "T")
            alert(dataNote);
        else
            document.location = dataNote;
    });
}


function viewAllNotes() {
    $.get('/mainPage/update').then(function (noteView) {
        if (noteView !== "") {
            $('#noteList').empty();
            var element = document.getElementById('noteList');
            var setNoteName, setNoteDescription, setNoteId, setNoteEndTime, setType;
            var parsed = JSON.parse(noteView);
            parsed.forEach((elem) => {
                setNoteName = elem.noteName;
                setNoteDescription = elem.noteDescription;
                setNoteId = elem.noteId;
                setNoteEndTime = elem.noteEndTime;
                setType = elem.noteType;

                if(setNoteDescription.length > 149){
                    setNoteDescription = setNoteDescription.substring(0,149) + '...'
                }
                let block = document.createElement('div')
                block.className = 'blc'
                let naz = document.createElement('h2')
                naz.textContent = setNoteName
                var button= document.createElement('button');
                button.className = "btn";
                button.type = "submit";
                button.id = setNoteId;
                button.onclick = function () {
                    clickNote(this);
                };
                button.textContent = "Watch";
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
                element.appendChild(block)
                block.appendChild(button)
            })
        }
    });
}

$(document).ready(function () {
    viewAllNotes();
})