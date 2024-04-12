import {Header}  from '/header.js';

function homeChild(props){
    const[name, setName] =useState("asirinaidu");
    setItem("homeInfo",{"key": "my name",""});
    setItem("jwtToken","asdfasdfasldfkjalsjdfasdf");
    clearItem("");
    userEffect(input->{
        fetch.get();
        await let  body = axios.get("url",header).then((resolve, exce)->)
        .catch(err->{
        });
    }[...]);

    return(
        <div>
            <h1> Hi how are you {props.homeInfo}
        </div>
    );
}

export default home;

class HomeParent extends React.Component{
    constructor(){
        this.state={
            "homeInfo" : {name: "naidu",address:"HYD"}
        };
    }

    render{
        return (<div>
            <HomeChild homeInfo={{this.state.homeInfo}}/>
        </div>);
    }
}

export Default Home;
