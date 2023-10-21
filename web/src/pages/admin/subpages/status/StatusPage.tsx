import {Card, Col, Row} from "antd";
import {CommonController} from "../../../../common/controller/CommonController";
import {useEffect, useState} from "react";

const cardStyle = {
    backgroundColor: '#6EB787',
    margin: '10px',
};

const commonController = new CommonController();

export const StatusPage: React.FC = () => {

    const [cpuUsage, setCpuUsage] = useState<string>('');
    const [freeMemory, setFreeMemory] = useState<string>('');
    const [freeDisk, setFreeDisk] = useState<string>('');


    useEffect( () => {
        const checkTokenAndRedirectIfError = async () => {
            let usages = await commonController.systemStatus();
            setCpuUsage(usages.systemLoadAverage!.toString())
            setFreeMemory(usages.freeMemory.toString())
            setFreeDisk(usages.freeSpace.toString())
        };

        checkTokenAndRedirectIfError()


    }, []);

    return (
        <Row gutter={16}>
            <Col span={8}>
                <Card style={cardStyle} title="CPU Usages">
                    {cpuUsage}
                </Card>
            </Col>
            <Col span={8}>
                <Card style={cardStyle} title="Free Memory(GB)">
                    {freeMemory}
                </Card>
            </Col>
            <Col span={8}>
                <Card style={cardStyle} title="Free Disk(GB)">
                    {freeDisk}
                </Card>
            </Col>
        </Row>
    );
};
