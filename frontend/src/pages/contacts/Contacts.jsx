import { Contact, Plus } from "lucide-react";

export default function Contacts() {
  return (
    <div className="page">
      <div className="page-heading">
        <div>
          <h2>Contacts</h2>
          <p>
            Manage your customer contacts.
          </p>
        </div>

        <button className="primary-button">
          <Plus size={18} />
          Add Contact
        </button>
      </div>

      <div className="panel">
        <div className="module-placeholder">
          <Contact size={48} />
          <h3>Contacts Management</h3>
          <p>
            Your contacts will appear here
            once connected to the backend.
          </p>
        </div>
      </div>
    </div>
  );
}