package test.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import test.dao.BirthDao;
import test.dto.BirthDto;

public class birthFrame extends JFrame 
				implements ActionListener,PropertyChangeListener{
	JTextField inputName, inputBirthday;
	DefaultTableModel model;
	JTable table;
	
	public birthFrame() {
		setLayout(new BorderLayout());
		
		JLabel label1=new JLabel("이름");
		inputName=new JTextField(10);
		
		JLabel label2=new JLabel("생일");
		inputBirthday=new JTextField(10);
		
		JButton saveBtn=new JButton("저장");
		saveBtn.setActionCommand("save");
		saveBtn.addActionListener(this);
		
		JButton deleteBtn=new JButton("삭제");
		deleteBtn.setActionCommand("delete");
		deleteBtn.addActionListener(this);
		
		JPanel panel=new JPanel();
		panel.add(label1);
		panel.add(inputName);
		panel.add(label2);
		panel.add(inputBirthday);
		panel.add(saveBtn);
		panel.add(deleteBtn);
		
		add(panel, BorderLayout.NORTH);
		
		table=new JTable();
		String[] colNames= {"번호", "이름", "생일"};
		model=new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column==0) {
					return false;
				}
				return true;
			}
		};
		table.setModel(model);
		JScrollPane scroll=new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
		displayMember();
		table.addPropertyChangeListener(this);
	}
	public void displayMember() {
		model.setRowCount(0);
		BirthDao dao=BirthDao.getInstance();
		List<BirthDto> list=dao.getList();
		for(BirthDto tmp:list) {
			Object[] row= {tmp.getNum(), tmp.getName(), tmp.getBirthday()};
			model.addRow(row);
		}
	}
	
	public static void main(String[] args) {
		birthFrame f=new birthFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 800, 500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		if(command.equals("save")) {
			String name=inputName.getText();
			String birthday=inputBirthday.getText();
			BirthDto dto=new BirthDto();
			dto.setName(name);
			dto.setBirthday(birthday);
			BirthDao dao=BirthDao.getInstance();
			boolean isSuccess=dao.insert(dto);
			if(isSuccess) {
				JOptionPane.showMessageDialog(this, name+" 님의 정보 추가 했습니다.");
			}else {
				JOptionPane.showMessageDialog(this, "추가 실패!");
			}
			displayMember();
		}else if(command.equals("delete")) {
			int selectedIndex=table.getSelectedRow();
			if(selectedIndex==-1) {
				return;
			}
			
			int selection=JOptionPane.showConfirmDialog(this, "선택된 row 를 삭제하겠습니까?");
			if(selection != JOptionPane.YES_OPTION) {
				return;
			}
			int num=(int)model.getValueAt(selectedIndex, 0);
			BirthDao dao=BirthDao.getInstance();
			dao.delete(num);
			displayMember();
		}
	}
	boolean isEditing=false;
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("property change!");
		System.out.println(evt.getPropertyName());
		if(evt.getPropertyName().equals("tableCellEditor")) {
			if(isEditing) {//수정중일때 
				//변화된 값을 읽어와서 DB 에 반영한다. 
				//수정된 칼럼에 있는 row  전체의 값을 읽어온다. 
				int selectedIndex=table.getSelectedRow();
				int num=(int)model.getValueAt(selectedIndex, 0);
				String name=(String)model.getValueAt(selectedIndex, 1);
				//수정할 회원의 정보를 MemberDto 객체에 담고 
				BirthDto dto=new BirthDto();
				dto.setNum(num);
				dto.setName(name);
				//DB에 저장하기 
				BirthDao.getInstance().update(dto);
				isEditing=false;
			}
			isEditing=true;
		}
	}
}