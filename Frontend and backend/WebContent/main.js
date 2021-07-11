var modal = document.getElementById("addmod");
var button = document.getElementById("open");
//document.getElementById("button").disabled = true;
var span = document.getElementsByClassName("close")[0];
var span1 = document.getElementsByClassName("close1")[0];
button.onclick = function() {
    modal.style.display = 'block';
}
span.onclick = function() {
    modal.style.display = "none";

}
span1.onclick = function() {
    modal.style.display = "none";

}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}



var modala = document.getElementById("editwala");
var buttona = document.getElementById("open1");
var spana = document.getElementsByClassName("closee")[0];
const span1a = document.getElementsByClassName("close0")[0];
buttona.onclick = function() {
    modala.style.display = 'block';
}
spana.onclick = function() {
    modala.style.display = "none";

}
span1a.onclick = function() {
    modala.style.display = "none";

}
window.onclick = function(event) {
    if (event.target == modala) {
        modala.style.display = "none";
    }
}
var modalaa = document.getElementById("delmod1");
var buttonaa = document.getElementById("op");
var spanaa = document.getElementsByClassName("closeee")[0];
const span1aa = document.getElementsByClassName("close22")[0];
buttonaa.onclick = function() {
    modalaa.style.display = 'block';
}
spanaa.onclick = function() {
    modalaa.style.display = "none";

}
span1aa.onclick = function() {
    modalaa.style.display = "none";

}
window.onclick = function(event) {
    if (event.target == modalaa) {
        modalaa.style.display = "none";
    }
}

function toggle(source) {
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i] != source)
            checkboxes[i].checked = source.checked;
    }
}

const showHeader=()=>{
    const headerData=JSON.parse(headers);
    const tableMain=document.getElementById("main-table");
    let tableRowEle='<tr class="header">';
    headerData.forEach(tableRow => {
        tableRowEle+=`<th class='${String(tableRow).toLowerCase()}'>`+String(tableRow)+'</th>';
        
    });
    tableRowEle+='</tr>';
    tableMain.innerHTML+=tableRowEle;
}

const showTableonload=(check=true)=>{
    if(check)
    {
        
        showHeader();//

    }
    const headerData=data;
    const tableMain=document.getElementById("main-table");
    headerData.forEach(tableRow=>{
        let tableRowEle='<tr>';
        Object.entries(tableRow).forEach(entry=>{
            const[key,value]=entry;
            tableRowEle+=`<td class="${key}">`+value+'</td>';
        });
        tableRowEle+='</tr>';
        tableMain.innerHtml+=tableRowEle;
    });

}

const fetchTabledata=()=>{
    fetch('http://localhost:8080/H2HBABBA2553/fetch')
    .then(response=>{
        return response.json()
    })
    .then(jsonResult=>{
        showTableonload(jsonResult)
    })
    .catch(error=>{
        console.log(error)
    })
}
(
    function(){
        fetchTabledata()
    }
)()

function clearcontent(elementID)
{
    document.getElementById(elementID).innerHTML="";
}

const myPaginationfunc=()=>
{
    clearcontent('tableyeh');
    start=start+1;
    showTable();

}
const myPaginationfunctwo=()=>
{
    clearcontent('tableyeh');
    
    start=start-1;
    showTable();

}

const submitnow=()=>{
    const name_customer=document.getElementById("name_customer".value);
    const cust_number=document.getElementById("cust_number".value);
    const invoice_id=document.getElementById("invoice_id".value);
    const due_in_date=document.getElementById("due_in_date".value);
    const total_open_amount=document.getElementById("total_open_amount".value);
    fetch(`http://localhost:8080/H2HBABBA2553/add?name_customer=${name_customer}&cust_number=${cust_number}&invoice_id=${invoice_id}&due_in_date=${due_in_date}&total_open_amount=${total_open_amount}`,
    {
        method:'POST'
    }).then(()=>{
        showTable()
    })
}

