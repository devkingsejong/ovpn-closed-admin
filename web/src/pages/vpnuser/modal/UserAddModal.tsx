import {Form, Input, Modal} from "antd";

interface UserAddModalProps {
    visible: boolean;
    onCancel: () => void;
    onAddUser: (values: { nickname: string; email: string; purePassword: string }) => void;
}

export const UserAddModal: React.FC<UserAddModalProps> = ({ visible, onCancel, onAddUser }) => {
    const [form] = Form.useForm();

    const handleOk = () => {
        form
            .validateFields()
            .then(values => {
                form.resetFields();
                onAddUser(values);
            })
            .catch(info => console.log("Validate Failed:", info));
    };

    return (
        <Modal
            title="Create New Vpn User"
            visible={visible}
            onOk={handleOk}
            onCancel={onCancel}
        >
            <Form
                form={form}
                layout="vertical"
                name="userAddForm"
                initialValues={{ modifier: 'public' }}
            >
                <Form.Item
                    name="nickname"
                    label="nickname"
                    rules={[{ required: true, message: 'Please input nickname.' }]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="email"
                    label="email"
                    rules={[{ required: true, message: 'Please input email!', type: 'email' }]}
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="purePassword"
                    label="password"
                    rules={[{ required: true, message: 'Please input password!' }]}
                >
                    <Input.Password />
                </Form.Item>
            </Form>
        </Modal>
    );
};
