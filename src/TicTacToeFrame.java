import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeFrame extends JFrame
{
    private char currentPlayer;
    private JButton[][] buttons;
    private char[][] board;
    private boolean gameOver;
    private JButton exitButton;

    public TicTacToeFrame()
    {

        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));


        //array of buttons
        buttons = new JButton[3][3];

        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                buttons[row][col] = new JButton("");
                buttons[row][col].addActionListener(new ButtonListener(row, col));
                buttonPanel.add(buttons[row][col]);
            }
        }

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new exitListener());
        add(exitButton, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.CENTER);
        setSize(800, 800);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener
    {
        private int row;
        private int col;

        public ButtonListener(int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) //check wins
        {
            if (!gameOver)
            {
                if (board[row][col] == '\u0000')
                {
                    board[row][col] = currentPlayer;
                    buttons[row][col].setText(Character.toString(currentPlayer));
                    if (checkWin(currentPlayer))
                    {
                        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                        gameOver = true;
                        playAgain();

                    } else if (checkTie())
                    {
                        JOptionPane.showMessageDialog(null, "Tie game!");
                        gameOver = true;
                        playAgain();
                    } else
                    {

                        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Square occupied, choose another.");
                }
            }
        }
    }
    //check for a win
    private boolean checkWin(char player)
    {
        // check rows
        for (int row = 0; row < 3; row++)
        {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // check columns
        for (int col = 0; col < 3; col++)
        {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player)
            {
                return true;
            }
        }

        // check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
        {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
        {
            return true;
        }

        return false;
    }

    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++)
            {
                if (board[row][col] == '\u0000')
                {

                    return false;
                }
            }
        }

        return true;
    }

    private void playAgain()
    {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col] = '\u0000';         // reset game
                    buttons[row][col].setText("");
                    buttons[row][col].setEnabled(true);
                }
            }
            currentPlayer = 'X';
            gameOver = false;
        } else {
            System.exit(0);
        }
    }

    private class exitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
