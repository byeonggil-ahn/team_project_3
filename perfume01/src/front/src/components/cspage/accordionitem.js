import React from "react";
import { Accordion } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

const Accordionitem = ({ eventKey, accordionhead, accordionbody }) => {
    return (
        <Accordion>
            <Accordion.Item eventKey={eventKey} className="cspage_accrodion">
                <Accordion.Header>{accordionhead}</Accordion.Header>
                <Accordion.Body>
                    {accordionbody}
                </Accordion.Body>
            </Accordion.Item>
        </Accordion>
    )
}

export default Accordionitem;