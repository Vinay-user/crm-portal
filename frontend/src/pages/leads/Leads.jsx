import { useEffect, useState } from "react";
import { Plus, Search } from "lucide-react";

import leadService from "../../services/leadService";
import DataTable from "../../components/tables/DataTable";
import { formatStatus } from "../../utils/formatters";

export default function Leads() {
  const [leads, setLeads] = useState([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  useEffect(() => {
    loadLeads();
  }, []);

  async function loadLeads() {
    try {
      setLoading(true);

      const response =
        await leadService.list();

      setLeads(
        response.data?.content ||
        response.data ||
        []
      );
    } catch {
      setLeads([]);
    } finally {
      setLoading(false);
    }
  }

  const filtered = leads.filter((lead) =>
    `${lead.firstName || ""} ${lead.lastName || ""} ${lead.email || ""}`
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  const columns = [
    {
      key: "firstName",
      label: "Name",
      render: (_, row) =>
        `${row.firstName || ""} ${row.lastName || ""}`
    },
    {
      key: "email",
      label: "Email"
    },
    {
      key: "company",
      label: "Company"
    },
    {
      key: "status",
      label: "Status",
      render: (value) => (
        <span className="status-badge">
          {formatStatus(value || "NEW")}
        </span>
      )
    },
    {
      key: "source",
      label: "Source"
    }
  ];

  return (
    <div className="page">
      <div className="page-heading">
        <div>
          <h2>Leads</h2>
          <p>
            Manage and qualify your sales leads.
          </p>
        </div>

        <button className="primary-button">
          <Plus size={18} />
          Add Lead
        </button>
      </div>

      <div className="panel">
        <div className="toolbar">
          <div className="search-box">
            <Search size={18} />

            <input
              placeholder="Search leads..."
              value={search}
              onChange={(event) =>
                setSearch(event.target.value)
              }
            />
          </div>
        </div>

        <DataTable
          columns={columns}
          data={filtered}
          loading={loading}
        />
      </div>
    </div>
  );
}