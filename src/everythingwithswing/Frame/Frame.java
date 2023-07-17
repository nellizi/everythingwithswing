package everythingwithswing.Frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import everythingwithswing.utils.AppService;

public class Frame {

	private JFrame frame;
	private JTextField searchTextField;
	private DefaultTableModel tableModel; // 테이블 데이터 편집하기 위해 필요
	private JTable table;
	private JPanel panelTop;
	private JPanel tablePanel;
	private AppService appService;
	private String[][] data;

	// Launch the application.
//	public static void main(String[] args) {
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frame window = new Frame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	// Create the application.
	public Frame() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Frame window = new Frame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	
		initialize();
	}

	// Initialize the contents of the frame.
	private void initialize() {



		appService = new AppService();

		// 패널 배치
		frame = new JFrame("EveryThing_By_Liz"); // 메인 프레임
		frame.setSize(1024, 768); // 프레임 크기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료 버튼 누르면 프로그램 종료
		frame.setLocationRelativeTo(null); // 프레임을 화면 중앙에 배치
		frame.setResizable(false); // 프레임 크기를 변경하지 못하도록 설정
		frame.getContentPane().setLayout(null); // 프레임에 추가되는 컴포넌트 레이아웃 -> Absolute

		panelTop = new JPanel();

		// 상단 패널
		panelTop.setBounds(6, 6, 1012, 713); // 패널 위치와 크기 -> (x, y, w, h)
		panelTop.setLayout(null); // 컴포넌트 레이아웃 -> Absolute
		frame.getContentPane().add(panelTop);
		panelTop.setVisible(true); // 패널 보이기

		tablePanel = new JPanel(); // 테이블 패널 생성
		tablePanel.setBounds(20, 50, 972, 650); // 테이블 패널 위치와 크기
		frame.getContentPane().add(tablePanel); // 테이블 패널 추가

		// 검색 필드
		JButton btnSearch = new JButton("검색");
		btnSearch.setBounds(881, 9, 100, 29);
		panelTop.add(btnSearch); // 상단 패널에 붙이기

		///////////////// 검색
		searchTextField = new JTextField(); // 검색어 입력 텍스트필드 생성
		searchTextField.setBounds(12, 9, 857, 30);
		searchTextField.setColumns(10); // 검색어 길이 설정
		panelTop.add(searchTextField); // 상단 패널에 붙이기

		searchTextField.addKeyListener(new KeyAdapter() { // 검색어 입력 텍스트필드 이벤트
			public void keyReleased(KeyEvent e) {
				String val = searchTextField.getText();
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(val));
			}
		});
		
		frame.setVisible(true);

		// 이벤트
		showTable();

	}

	/** UI테이블 설정 */

	public void showTable() {
		data = appService.getList();

		String[][] row = new String[][] { { "394116", "이지현", "123", "123" } };

		String[] header = new String[] { "이름", "경로", "크기", "수정한 날짜" };

		tableModel = new DefaultTableModel(data, header);
		table = new JTable(tableModel) { // 셀에서 편집할 수 없게
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setBounds(50, 50, 975, 255);

		// 셀 값 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
		JLabel headerLabel = (JLabel) rendererFromHeader;
		headerLabel.setHorizontalAlignment(JLabel.CENTER);

		// 컬럼 크기
		TableColumnModel colModel = table.getColumnModel();
		colModel.getColumn(0).setPreferredWidth(30);
		colModel.getColumn(1).setPreferredWidth(300);
		colModel.getColumn(2).setPreferredWidth(15);
		colModel.getColumn(3).setPreferredWidth(100);

		table.getTableHeader().setFont(new Font("NanumGothic", Font.BOLD, 13)); // header 폰트 설정
		table.getTableHeader().setPreferredSize(new Dimension(100, 30)); // header 넓이, 높이
		table.setFont(new Font("NanumGothic", Font.PLAIN, 13)); // 셀 폰트, 크기
		table.setRowHeight(30);

		// 테이블에 스크롤바 추가
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(975, 645));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0)); // padding(상, 좌, 하, 우)
		tablePanel.add(scrollPane); // JScrollPane을 panelTop에 바로 올리면 안 보임. 전용 tablePanel에 올려야 보임

	}

}