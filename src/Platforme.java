import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Platforme {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Platforme::createPlatforme);
        // SwingUtilities.invokeLater(() -> Platforme.createPlatforme());
        /*

        return rs.next() ? rs.getString("role") : null;
        
        et

        if (rs.next()) {
            return rs.getString("role");
        } else {
            return null;
        }

        sont egales
        */
        
        /*
        loginButton.addActionListener(e -> { 
            // code
        });

        et 

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // code
            }
        });

        sont egales
        */
    }

    private static void createPlatforme() {
        JFrame frame = new JFrame("Platforme de formations");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // inisialisation du mainpanel
        JPanel loginPanel = createLoginPanel(mainPanel, cardLayout);
        mainPanel.add(loginPanel, "Login");
        cardLayout.show(mainPanel, "Login");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // creation de la panel de connexion
    private static JPanel createLoginPanel(JPanel mainPanel, CardLayout cardLayout) {
        JPanel panel = new JPanel(null);

        JLabel titleLabel = new JLabel("PLATFORME DE FORMATIONS");
        titleLabel.setBounds(200, 35, 400, 25);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        JLabel emailLabel = new JLabel("E-mail :");
        emailLabel.setBounds(100, 150, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(250, 150, 165, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setBounds(100, 250, 100, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(250, 250, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Connexion");
        loginButton.setBounds(300, 350, 160, 30);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String email = emailText.getText();
            String password = new String(passwordText.getPassword());
            int userId = Database.checkLogin(email, password);
            if (userId > 0) {
                createProfilPanel(mainPanel, cardLayout, userId);
            } else {
                JOptionPane.showMessageDialog(panel, "Email ou mot de passe invalide!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JLabel signupLabel = new JLabel("ou");
        signupLabel.setBounds(480, 450, 20, 25);
        panel.add(signupLabel);

        JButton signupButton = new JButton("S'Inscrire");
        signupButton.setBounds(500, 450, 90, 20);
        panel.add(signupButton);

        signupButton.addActionListener(e -> {
            mainPanel.add(createSignupPanel(mainPanel, cardLayout), "Signup");
            cardLayout.show(mainPanel, "Signup");
        });

        return panel;
    }

    // creation de la panel d'inscription
    private static JPanel createSignupPanel(JPanel mainPanel, CardLayout cardLayout) {
        JPanel panel = new JPanel(null);
    
        // Titre label
        JLabel titreLabel = new JLabel("Inscription");
        titreLabel.setBounds(350, 20, 400, 25);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titreLabel);
    
        // name label
        JLabel nameLabel = new JLabel("Nom :");
        nameLabel.setBounds(100, 100, 80, 25);
        panel.add(nameLabel);
    
        // name text field
        JTextField nameText = new JTextField(20);
        nameText.setBounds(250, 100, 165, 25);
        panel.add(nameText);
    
        // email label
        JLabel emailLabel = new JLabel("E-mail :");
        emailLabel.setBounds(100, 150, 80, 25);
        panel.add(emailLabel);
    
        // email text field
        JTextField emailText = new JTextField(20);
        emailText.setBounds(250, 150, 165, 25);
        panel.add(emailText);
    
        // Password label
        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setBounds(100, 200, 100, 25);
        panel.add(passwordLabel);
    
        // Password text field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(250, 200, 165, 25);
        panel.add(passwordText);
    
        // Confirm label
        JLabel confirmLabel = new JLabel("Confirmer M.D.P :");
        confirmLabel.setBounds(100, 250, 120, 25);
        panel.add(confirmLabel);
    
        // Confirm text field
        JPasswordField confirmText = new JPasswordField(20);
        confirmText.setBounds(250, 250, 165, 25);
        panel.add(confirmText);
    
        // Role label
        JLabel roleLabel = new JLabel("Rôle :");
        roleLabel.setBounds(100, 300, 100, 25);
        panel.add(roleLabel);
    
        // Radio buttons for role selection
        JRadioButton etudiantRadio = new JRadioButton("Étudiant");
        etudiantRadio.setBounds(200, 300, 80, 25);
        JRadioButton formateurRadio = new JRadioButton("Formateur");
        formateurRadio.setBounds(300, 300, 100, 25);
    
        // Grouper les boutons radio
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(etudiantRadio);
        roleGroup.add(formateurRadio);
    
        // ajout a la panel
        panel.add(etudiantRadio);
        panel.add(formateurRadio);
    
        // Signup button
        JButton signupButton = new JButton("S'Inscrire");
        signupButton.setBounds(300, 350, 160, 30);
        panel.add(signupButton);
    
        // Action listener for the signup button
        signupButton.addActionListener(e -> {
            String name = nameText.getText();
            String email = emailText.getText();
            String password = new String(passwordText.getPassword());
            String confirm = new String(confirmText.getPassword());
            String role = etudiantRadio.isSelected() ? "Etudiant" : formateurRadio.isSelected() ? "Formateur" : null;

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(panel, "Tous les champs doivent être remplis!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(panel, "Le mot de passe et sa confirmation doivent être identiques!", "Erreur", JOptionPane.ERROR_MESSAGE);
                passwordText.setText("");
                confirmText.setText("");
            } else if (Database.registerUser(name, email, password, role)) {
                JOptionPane.showMessageDialog(panel, "Inscription réussie en tant que " + role + "!");
                mainPanel.add(createLoginPanel(mainPanel, cardLayout), "Login");
                cardLayout.show(mainPanel, "Login");
            } else {
                JOptionPane.showMessageDialog(panel, "Erreur lors de l'inscription. Email peut déjà être utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                // effacer les fields a remplir pour reesayer
                nameText.setText("");
                emailText.setText("");
                passwordText.setText("");
                confirmText.setText("");
                roleGroup.clearSelection();
            }
        });

        // Return button
        JButton returnButton = new JButton("Retour");
        returnButton.setBounds(500, 400, 100, 25);
        panel.add(returnButton);

        // Action listener for the return button
        returnButton.addActionListener(e -> {
            mainPanel.add(createLoginPanel(mainPanel, cardLayout), "Login");
            cardLayout.show(mainPanel, "Login");
        });

        return panel;
    }
    
    // creation de la panel du profil de l'etudiant
    private static JPanel createEtudiantProfilPanel(JPanel mainPanel, CardLayout cardLayout, int userId) {
        JPanel panel = new JPanel(new BorderLayout());
    
        JLabel titleLabel = new JLabel("Profil Étudiant : " + Database.getUserName(userId), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
    
        // labels pour les listes
        JLabel DisponibleLabel = new JLabel("Formations Disponibles", SwingConstants.CENTER);
        DisponibleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    
        JLabel inscritLabel = new JLabel("Mes Formations Inscrits", SwingConstants.CENTER);
        inscritLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    
        // listes models et JList
        DefaultListModel<String> DisponibleModel = new DefaultListModel<>();
        JList<String> DisponibleList = new JList<>(DisponibleModel);
        JScrollPane DisponibleScroll = new JScrollPane(DisponibleList);
    
        DefaultListModel<String> inscritModel = new DefaultListModel<>();
        JList<String> inscritList = new JList<>(inscritModel);
        JScrollPane inscritScroll = new JScrollPane(inscritList);
    
        // Split pane pour afficher les deux listes simultanement
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, DisponibleScroll, inscritScroll);
        panel.add(splitPane, BorderLayout.CENTER);
    
        // ajout labels des listes
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(DisponibleLabel, BorderLayout.NORTH);
        leftPanel.add(DisponibleScroll, BorderLayout.CENTER);
    
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(inscritLabel, BorderLayout.NORTH);
        rightPanel.add(inscritScroll, BorderLayout.CENTER);
    
        // Add panels to split pane
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
    
        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
    
            ResultSet rsDisponible = stmt.executeQuery(
                "SELECT id_formation, titre, (SELECT count(*) FROM inscription WHERE formation.id_formation = id_formation) nombre FROM formation WHERE id_formation NOT IN (" +
                "SELECT id_formation FROM inscription WHERE id_utilisateur = " + userId + ") ORDER BY(nombre) DESC"
            );
            while (rsDisponible.next()) {
                DisponibleModel.addElement(rsDisponible.getInt("id_formation") + " - " + rsDisponible.getString("titre") + " (" + rsDisponible.getInt("nombre") + ")");
            }
    
            ResultSet rsinscrit = stmt.executeQuery(
                "SELECT id_formation, titre, (SELECT count(*) FROM inscription WHERE formation.id_formation = id_formation) nombre FROM formation WHERE id_formation IN (" +
                "SELECT id_formation FROM inscription WHERE id_utilisateur = " + userId + ") ORDER BY(nombre) DESC"
            );
            while (rsinscrit.next()) {
                inscritModel.addElement(rsinscrit.getInt("id_formation") + " - " + rsinscrit.getString("titre") + " (" + rsinscrit.getInt("nombre") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DisponibleList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = DisponibleList.getSelectedValue();
                if (selectedValue != null) {
                    int formationId = Integer.parseInt(selectedValue.split(" - ")[0]);  // Extract the formation ID from the text
                    mainPanel.add(createFormationDetailsPanel(mainPanel, cardLayout, userId, formationId), "FormationDetails");
                    cardLayout.show(mainPanel, "FormationDetails");
                }
            }
        });

        inscritList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = inscritList.getSelectedValue();
                if (selectedValue != null) {
                    int formationId = Integer.parseInt(selectedValue.split(" - ")[0]);  // Extract the formation ID from the text
                    mainPanel.add(createFormationDetailsPanel(mainPanel, cardLayout, userId, formationId), "FormationDetails");
                    cardLayout.show(mainPanel, "FormationDetails");
                }
            }
        });
    
        // "Quitter" button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton quitterButton = new JButton("Quitter");
        buttonPanel.add(quitterButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        quitterButton.addActionListener(e -> {
            mainPanel.add(createLoginPanel(mainPanel, cardLayout), "Login");
            cardLayout.show(mainPanel, "Login");
        });
    
        return panel;
    }

    // creation de la panel du profil du formateur
    private static JPanel createFormateurProfilPanel(JPanel mainPanel, CardLayout cardLayout, int userId) {
        JPanel panel = new JPanel(new BorderLayout());
    
        JLabel titleLabel = new JLabel("Profil Formateur : " + Database.getUserName(userId), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);
    
        // labels pour les listes
        JLabel DisponibleLabel = new JLabel("Autres Formations", SwingConstants.CENTER);
        DisponibleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    
        JLabel createdLabel = new JLabel("Mes Formations", SwingConstants.CENTER);
        createdLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    
        // list models et JLists
        DefaultListModel<String> DisponibleModel = new DefaultListModel<>();
        JList<String> DisponibleList = new JList<>(DisponibleModel);
        JScrollPane DisponibleScroll = new JScrollPane(DisponibleList);
    
        DefaultListModel<String> createdModel = new DefaultListModel<>();
        JList<String> createdList = new JList<>(createdModel);
        JScrollPane createdScroll = new JScrollPane(createdList);
    
        // Split pane pour afficher les deux listes simultanement
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, DisponibleScroll, createdScroll);
        panel.add(splitPane, BorderLayout.CENTER);
    
        // ajout labels des listes
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(DisponibleLabel, BorderLayout.NORTH);
        leftPanel.add(DisponibleScroll, BorderLayout.CENTER);
    
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(createdLabel, BorderLayout.NORTH);
        rightPanel.add(createdScroll, BorderLayout.CENTER);
    
        // Add panels to split pane
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
    
        try (Connection conn = Database.getConnection()) {
            Statement stmt = conn.createStatement();
    
            ResultSet rsDisponible = stmt.executeQuery(
                "SELECT id_formation, titre, (SELECT count(*) FROM inscription WHERE formation.id_formation = id_formation) nombre FROM formation WHERE formateur != " + userId + " ORDER BY (nombre) DESC"
            );
            while (rsDisponible.next()) {
                DisponibleModel.addElement(rsDisponible.getInt("id_formation") + " - " + rsDisponible.getString("titre") + " (" + rsDisponible.getInt("nombre") + ")");
            }
    
            ResultSet rsCreated = stmt.executeQuery(
                "SELECT id_formation, titre, (SELECT count(*) FROM inscription WHERE formation.id_formation = id_formation) nombre FROM formation WHERE formateur = " + userId + " ORDER BY (nombre) DESC"
            );
            while (rsCreated.next()) {
                createdModel.addElement(rsCreated.getInt("id_formation") + " - " + rsCreated.getString("titre") + " (" + rsCreated.getInt("nombre") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DisponibleList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = DisponibleList.getSelectedValue();
                if (selectedValue != null) {
                    int formationId = Integer.parseInt(selectedValue.split(" - ")[0]);  // extraction du formation id du text
                    mainPanel.add(createFormationDetailsPanel(mainPanel, cardLayout, userId, formationId), "FormationDetails");
                    cardLayout.show(mainPanel, "FormationDetails");
                }
            }
        });

        createdList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedValue = createdList.getSelectedValue();
                if (selectedValue != null) {
                    int formationId = Integer.parseInt(selectedValue.split(" - ")[0]);  // extraction du formation id du text
                    mainPanel.add(createFormationDetailsPanel(mainPanel, cardLayout, userId, formationId), "FormationDetails");
                    cardLayout.show(mainPanel, "FormationDetails");
                }
            }
        });
    
        // buttom panel pour les bouttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // "Créer une formation" button
        JButton createButton = new JButton("Créer une formation");
        bottomPanel.add(createButton);
        createButton.addActionListener(e -> {
            mainPanel.add(createAddFormationPanel(mainPanel, cardLayout, userId), "AddFormation");
            cardLayout.show(mainPanel, "AddFormation");
        });
    
        // "Quitter" button
        JButton quitterButton = new JButton("Quitter");
        bottomPanel.add(quitterButton);
        quitterButton.addActionListener(e -> {
            mainPanel.add(createLoginPanel(mainPanel, cardLayout), "Login");
            cardLayout.show(mainPanel, "Login");  // Assuming the login panel is identified as "Login"
        });
    
        // ajouter bottom panel au bas de la frame
        panel.add(bottomPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    
    // creation de la panel de creation d'une formation
    private static JPanel createAddFormationPanel(JPanel mainPanel, CardLayout cardLayout, int formateurId) {
        JPanel panel = new JPanel(null);
    
        // Titre label
        JLabel titreLabel = new JLabel("Créer une formation");
        titreLabel.setBounds(350, 20, 400, 25);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titreLabel);
    
        // Title label
        JLabel titleLabelField = new JLabel("Titre de la formation:");
        titleLabelField.setBounds(100, 100, 150, 25);
        panel.add(titleLabelField);
    
        // Title text field
        JTextField titleField = new JTextField(20);
        titleField.setBounds(250, 100, 300, 25);
        panel.add(titleField);
    
        // Description label
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(100, 150, 150, 25);
        panel.add(descriptionLabel);
    
        // Description text area
        JTextArea descriptionArea = new JTextArea();
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        descriptionScroll.setBounds(250, 150, 300, 100);
        panel.add(descriptionScroll);
    
        // Prix label
        JLabel priceLabel = new JLabel("Prix:");
        priceLabel.setBounds(100, 270, 150, 25);
        panel.add(priceLabel);
    
        // Prix text field
        JTextField priceField = new JTextField(20);
        priceField.setBounds(250, 270, 300, 25);
        panel.add(priceField);
    
        // Submit button
        JButton submitButton = new JButton("Soumettre");
        submitButton.setBounds(250, 350, 120, 30);
        panel.add(submitButton);
    
        // Retour button
        JButton returnButton = new JButton("Retour");
        returnButton.setBounds(400, 350, 120, 30);
        panel.add(returnButton);
    
        // ActionListener pour Retour button
        returnButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Profil");
        });
    
        // ActionListener pour Soumettre button
        submitButton.addActionListener(e -> {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            String priceText = priceField.getText();
    
            if (!title.isEmpty() && !description.isEmpty() && !priceText.isEmpty()) {
                try {
                    // conversion du prix au double
                    double price = Double.parseDouble(priceText);
    
                    try (Connection conn = Database.getConnection()) {
                        String query = "INSERT INTO formation (titre, description, prix, formateur) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(query)) {
                            stmt.setString(1, title);
                            stmt.setString(2, description);
                            stmt.setDouble(3, price);
                            stmt.setInt(4, formateurId);
                            stmt.executeUpdate();
                            JOptionPane.showMessageDialog(panel, "Formation ajoutée avec succès!");
                            mainPanel.add(createFormateurProfilPanel(mainPanel, cardLayout, formateurId), "Profil");
                            cardLayout.show(mainPanel, "Profil");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Erreur lors de l'ajout de la formation.");
                    }
    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Veuillez entrer un prix valide.");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Veuillez remplir tous les champs.");
            }
        });
    
        return panel;
    }
    
    // creation de la panel de details d'une formation
    private static JPanel createFormationDetailsPanel(JPanel mainPanel, CardLayout cardLayout, int userId, int formationId) {
        JPanel panel = new JPanel(new BorderLayout());

        String title = "";
        String description = "";
        String formateurName = "";
        double prix = 0;
        int numberEtudiant = 0;

        final boolean[] isinscrit = new boolean[1];
        final boolean[] isCreator = new boolean[1];
    
        try (Connection conn = Database.getConnection()) {
            // obtenir details de la formation
            String query = "SELECT titre, description, formateur, prix FROM formation WHERE id_formation = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, formationId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    title = rs.getString("titre");
                    description = rs.getString("description");
                    formateurName = Database.getUserName(rs.getInt("formateur"));
                    prix = rs.getDouble("prix");
                }
            }
    
            // verification si l'etudiant est inscrit 
            String inscritQuery = "SELECT COUNT(*) FROM inscription WHERE id_formation = ? AND id_utilisateur = ?";
            try (PreparedStatement stmt = conn.prepareStatement(inscritQuery)) {
                stmt.setInt(1, formationId);
                stmt.setInt(2, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    isinscrit[0] = rs.getInt(1) > 0;
                }
            }
    
            // verifier si le formateur est le createur de cette formation
            String formateurQuery = "SELECT COUNT(*) FROM formation WHERE id_formation = ? AND formateur = ?";
            try (PreparedStatement stmt = conn.prepareStatement(formateurQuery)) {
                stmt.setInt(1, formationId);
                stmt.setInt(2, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    isCreator[0] = rs.getInt(1) > 0;
                }
            }

            // nombre des etudiants inscrits
            String numberQuery = "SELECT COUNT(*) FROM inscription WHERE id_formation = ?";
            try (PreparedStatement stmt = conn.prepareStatement(numberQuery)) {
                stmt.setInt(1, formationId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    numberEtudiant = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Format the details dans une label avec HTML
        String htmlContent = "<html>" +
                "<h1 style='text-align: center;'>&nbsp;&nbsp;&nbsp;&nbsp;" + title + "</h1><br><br>" +
                "<p style='font-size: 14px;'>&nbsp;&nbsp;&nbsp;&nbsp;Description:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + description.replace("\n", "<br>") + "</p><br>" +
                "<p style='font-weight: bold; text-align: left;'>&nbsp;&nbsp;&nbsp;&nbsp;Formateur: " + formateurName + "</p><br>" +
                "<p style='font-weight: bold; text-align: left;'>&nbsp;&nbsp;&nbsp;&nbsp;Prix: " + prix + " Dt</p><br>" +
                "<p style='font-weight: bold; text-align: left;'>&nbsp;&nbsp;&nbsp;&nbsp;Nombre des Etudiants inscrits: " + numberEtudiant + "</p>" +
                "</html>";
    
        JLabel contentLabel = new JLabel(htmlContent, SwingConstants.LEFT);
        panel.add(contentLabel, BorderLayout.CENTER);
    
        // creation des buttom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
        // bouton s'inscrire ou desinscrire
        if ("Etudiant".equals(Database.getUserRole(userId))) {
            JButton actionButton = new JButton(isinscrit[0] ? "Désinscrire" : "S'inscrire");
            buttonPanel.add(actionButton);
            actionButton.addActionListener(e -> {
                handleEtudiantAction(mainPanel, cardLayout, formationId, userId, isinscrit[0]);
            });
        } else {
            // Formateur peut supprimer si il est le createur
            if (isCreator[0]) {
                JButton deleteButton = new JButton("Supprimer la formation");
                buttonPanel.add(deleteButton);
                deleteButton.addActionListener(e -> {
                    handleFormateurAction(mainPanel, cardLayout, formationId, userId);
                });
            }
        }
    
        // bouton retour au profil
        JButton returnButton = new JButton("Retour");
        buttonPanel.add(returnButton);
        returnButton.addActionListener(e -> {
            createProfilPanel(mainPanel, cardLayout, userId);
        });
    
        panel.add(buttonPanel, BorderLayout.SOUTH);
    
        return panel;
    }
    
    // Etudiant inscription / desinscription
    private static void handleEtudiantAction(JPanel mainPanel, CardLayout cardLayout, int formationId, int userId, boolean isinscrit) {
        try (Connection conn = Database.getConnection()) {
            if (isinscrit) {
                // desinscription
                String unsubscribeQuery = "DELETE FROM inscription WHERE id_formation = ? AND id_utilisateur = ?";
                try (PreparedStatement stmt = conn.prepareStatement(unsubscribeQuery)) {
                    stmt.setInt(1, formationId);
                    stmt.setInt(2, userId);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Vous vous êtes désinscrit.");
                    createProfilPanel(mainPanel, cardLayout, userId);
                }
            } else {
                // inscription
                String registerQuery = "INSERT INTO inscription (id_formation, id_utilisateur) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(registerQuery)) {
                    stmt.setInt(1, formationId);
                    stmt.setInt(2, userId);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(mainPanel, "Vous êtes inscrit avec succès.");
                    createProfilPanel(mainPanel, cardLayout, userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Formateur supression
    private static void handleFormateurAction(JPanel mainPanel, CardLayout cardLayout, int formationId, int userId) {
        try (Connection conn = Database.getConnection()) {
            // supression tout occurences dans inscription
            String deleteInscriptionsQuery = "DELETE FROM inscription WHERE id_formation = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteInscriptionsQuery)) {
                stmt.setInt(1, formationId);
                stmt.executeUpdate();
            }
    
            // supression dans formation
            String deleteFormationQuery = "DELETE FROM formation WHERE id_formation = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteFormationQuery)) {
                stmt.setInt(1, formationId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Formation supprimée.");
                createProfilPanel(mainPanel, cardLayout, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de la formation.");
        } 
    }

    // creation du Profil Panel (FormateurPanel ou EtudiantPanel)
    private static void createProfilPanel(JPanel mainPanel, CardLayout cardLayout, int userId) {
        String role = Database.getUserRole(userId);
        JPanel profilPanel = role.equals("Etudiant") ? 
                createEtudiantProfilPanel(mainPanel, cardLayout, userId) : 
                createFormateurProfilPanel(mainPanel, cardLayout, userId);
        mainPanel.add(profilPanel, "Profil");
        cardLayout.show(mainPanel, "Profil");
    }
}

class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/platforme";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    // connecter a la base des donnees
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // verifier credentiels de connection 
    public static int checkLogin(String email, String password) {
        String query = "SELECT * FROM utilisateur WHERE email = ? AND motDePasse = ?";
        try (Connection conn = getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("id_utilisateur") : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // inscription d'un nouveau utilisateur
    public static boolean registerUser(String name, String email, String password, String role) {
        // Ensure role is either "Etudiant" or "Formateur"
        if (!role.equals("Etudiant") && !role.equals("Formateur")) {
            return false; // Invalid role
        }
        
        String query = "INSERT INTO utilisateur (nom, email, motDePasse, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.executeUpdate();
            return true; // Registration successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Registration failed
        }
    }

    // retourne le role d'un utilisateur par son id
    public static String getUserRole(int userId) {
        String query = "SELECT role FROM utilisateur WHERE id_utilisateur = ?";
        try (Connection conn = getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("role") : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // retourne le nom d'un utilisateur par son id
    public static String getUserName(int userId) {
        String query = "SELECT nom FROM utilisateur WHERE id_utilisateur = ?";
        try (Connection conn = getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("nom") : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}