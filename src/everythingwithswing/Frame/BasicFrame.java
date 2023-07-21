package everythingwithswing.Frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class BasicFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	// 기본으로 수행되어랴 하는 것들
	public BasicFrame() {
		super("창1"); //타이틀
        JPanel jPanel = new JPanel();
        JTextField address=new JTextField(20);
        jPanel.add(address);
        JButton btn1 = new JButton("검색");
        setSize(300, 200); //창 크기 설정
        jPanel.add(btn1);
        add(jPanel);
 
        Dimension frameSize = getSize();
        //해상도 값 
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
 
        
        //이벤트? 실행되는 시점을 알려주기 위함
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Frame(address.getText());
               
                setVisible(false); // 창 안보이게 하기 
            }
        });
	}
	
}
